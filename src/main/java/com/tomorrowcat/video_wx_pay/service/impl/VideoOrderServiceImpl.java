package com.tomorrowcat.video_wx_pay.service.impl;

import com.tomorrowcat.video_wx_pay.config.WeChatConfig;
import com.tomorrowcat.video_wx_pay.domain.User;
import com.tomorrowcat.video_wx_pay.domain.Video;
import com.tomorrowcat.video_wx_pay.domain.VideoOrder;
import com.tomorrowcat.video_wx_pay.dto.VideoOrderDto;
import com.tomorrowcat.video_wx_pay.exception.XdException;
import com.tomorrowcat.video_wx_pay.mapper.UserMapper;
import com.tomorrowcat.video_wx_pay.mapper.VideoMapper;
import com.tomorrowcat.video_wx_pay.mapper.VideoOrderMapper;
import com.tomorrowcat.video_wx_pay.service.VideoOrderService;
import com.tomorrowcat.video_wx_pay.utils.CommonUtils;
import com.tomorrowcat.video_wx_pay.utils.HttpUtils;
import com.tomorrowcat.video_wx_pay.utils.WXPayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @description:
 * @author: kim
 * @create: 2021-07-02 10:47
 * @version: 1.0.0
 */
@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeChatConfig weChatConfig;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");

    /**
     * @description: 用户下单
     * @param: videoOrderDto
     * @return: VideoOrder
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)   //开启事务
    public String save(VideoOrderDto videoOrderDto) throws Exception {


        dataLogger.info("module=video_order`api=save`user_id={}`video_id={}", videoOrderDto.getUserId(), videoOrderDto.getVideoId());


        //查找视频信息
        Video video = videoMapper.findById(videoOrderDto.getVideoId());
        //查找用户信息
        User user = userMapper.findById(videoOrderDto.getUserId());

        if (video != null && user != null) {
            //生成订单
            VideoOrder videoOrder = new VideoOrder();
            videoOrder.setTotalFee(video.getPrice());
            videoOrder.setVideoImg(video.getCoverImg());
            videoOrder.setVideoTitle(video.getTitle());
            videoOrder.setCreateTime(new Date());
            videoOrder.setVideoId(video.getId());
            videoOrder.setState(0);
            videoOrder.setUserId(user.getId());
            videoOrder.setHeadImg(user.getHeadImg());
            videoOrder.setNickname(user.getName());
            //0--未删除
            videoOrder.setDel(0);
            videoOrder.setIp(videoOrderDto.getIp());
            videoOrder.setOutTradeNo(CommonUtils.generateUUID());
            //生成本地预支付订单
            videoOrderMapper.insert(videoOrder);


            //统一下单，获取codeurl
            String codeUrl = unifiedOrder(videoOrder);

            if (codeUrl == null) {
                throw new XdException(-5, "code_url为空");
            }

            //codeUrl用于生成支付二维码
            return codeUrl;

        }else{
            throw new XdException(-5, "视频或用户不存在！");

        }


    }


    /**
     * 统一下单方法
     * 这些参数的意思：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
     *
     * @return
     */
    private String unifiedOrder(VideoOrder videoOrder) throws Exception {
        //sortedMap让map元素有序
        SortedMap<String, String> params = new TreeMap<>();
        params.put("appid", weChatConfig.getAppId());
        params.put("mch_id", weChatConfig.getMchId());
        //随机数
        params.put("nonce_str", CommonUtils.generateUUID());
        params.put("body", videoOrder.getVideoTitle());
        params.put("out_trade_no", videoOrder.getOutTradeNo());
        params.put("total_fee", videoOrder.getTotalFee().toString());
        //下单用户所登陆客户端的ip
        params.put("spbill_create_ip", videoOrder.getIp());
        //回调接口，支付完成后微信平台要回调此接口
        params.put("notify_url", weChatConfig.getPayCallbackUrl());
        //trade_type  交易类型   扫码支付
        params.put("trade_type", "NATIVE");

        //生成签名
        String sign = WXPayUtils.createSign(params, weChatConfig.getKey());
        //自包含
        params.put("sign", sign);

        //map转xml
        String payXml = WXPayUtils.mapToXml(params);

        System.out.println("统一下单请求参数：\n" + payXml);

        //统一下单    微信方⽣成预⽀付交易单
        String responseXML = HttpUtils.doPost(WeChatConfig.getUnifiedOrderUrl(), payXml, 4000);
        if (null == responseXML) {
            return null;
        }

        responseXML = new String(responseXML.getBytes("ISO-8859-1"), "utf-8");
        Map<String, String> responseMap = WXPayUtils.xmlToMap(responseXML);
        System.out.println("统一下单响应内容：" + responseMap.toString());
        if (responseMap != null) {
            //预支付交易链接code_url, code_Url用于生成支付二维码
            return responseMap.get("code_url");
        }


        return null;


    }

    /**
     * @description: 通过订单号查订单
     * @param: outTradeNo
     * @return: VideoOrder
     */
    @Override
    public VideoOrder findByOutTradeNo(String outTradeNo) {
        return videoOrderMapper.findByOutTradeNo(outTradeNo);
    }

    /**
     * @description: 更新订单
     * @param: videoOrder
     * @return: int
     */
    @Override
    public int updateVideoOrderByOutTradeNo(VideoOrder videoOrder) {
        return videoOrderMapper.updateVideoOrderByOutTradeNo(videoOrder);
    }


}







