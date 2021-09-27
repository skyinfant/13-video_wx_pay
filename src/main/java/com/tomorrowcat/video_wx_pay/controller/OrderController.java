package com.tomorrowcat.video_wx_pay.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.tomorrowcat.video_wx_pay.domain.JsonData;
import com.tomorrowcat.video_wx_pay.domain.VideoOrder;
import com.tomorrowcat.video_wx_pay.dto.VideoOrderDto;
import com.tomorrowcat.video_wx_pay.exception.XdException;
import com.tomorrowcat.video_wx_pay.service.VideoOrderService;
import com.tomorrowcat.video_wx_pay.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单接口
 */
@RestController
@RequestMapping("/user/api/v1/order")
//@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * @description: 用户下单。用户在页面点击购买按钮，然后就会调这个方法
     * @param: null
     * @return: null
     */
    @GetMapping("add")
    public void saveOrder(@RequestParam(value = "video_id", required = true) int videoId,
                          HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ip = IpUtils.getIpAddr(request);

        //由于微信登陆功能目前无法完整实操，所以暂时把user_id写死
//        int userId = (int) request.getAttribute("user_id");

        int userId = 1;
        VideoOrderDto videoOrderDto = new VideoOrderDto();
        videoOrderDto.setUserId(userId);
        videoOrderDto.setVideoId(videoId);
        videoOrderDto.setIp(ip);
        //统一下单   codeUrl用于生成支付二维码
        String codeUrl = videoOrderService.save(videoOrderDto);


        //把codeUrl生成支付二维码,用户用微信扫描这个二维码就是在用微信访问这个链接
        try {
            //生成二维码配置
            Map<EncodeHintType, Object> hints = new HashMap<>();
            //设置纠错等级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //编码类型
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, 400, 400, hints);
            OutputStream out = response.getOutputStream();
            //把二维码以流发送到浏览器展示给用户
            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
