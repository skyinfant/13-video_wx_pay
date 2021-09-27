package com.tomorrowcat.video_wx_pay.controller;

import com.tomorrowcat.video_wx_pay.config.WeChatConfig;
import com.tomorrowcat.video_wx_pay.domain.JsonData;
import com.tomorrowcat.video_wx_pay.domain.User;
import com.tomorrowcat.video_wx_pay.domain.VideoOrder;
import com.tomorrowcat.video_wx_pay.service.UserService;
import com.tomorrowcat.video_wx_pay.service.VideoOrderService;
import com.tomorrowcat.video_wx_pay.utils.JwtUtils;
import com.tomorrowcat.video_wx_pay.utils.WXPayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @description:
 * @author: kim
 * @create: 2021-06-29 17:00
 * @version: 1.0.0
 */

@RestController
@RequestMapping("/api/v1/wechat")
public class WechatController {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * @description: 首页加载 --（携带state）访问此接口-->获取登陆二维码的url（包含appid，回调接口，state）赋值给登陆a标签的href属性
     * @param: accessPage   就是state，用于避免csrf攻击，有道笔记中有介绍.也可用于记录当前页面，以便登陆完成后跳转回来
     * @return: JsonData
     */
    @RequestMapping("login_url")
//    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page",required = true)String access_page) throws UnsupportedEncodingException {
        //开放平台回调接口   用户扫码确认授权登陆后微信方要回调的接口
        String redirectUrl = weChatConfig.getOpenRedirectUrl();
        //编码，因为可能包含特殊字符
        String callbackUrl = URLEncoder.encode(redirectUrl, "GBK");
        //微信授权登陆二维码的url   appid+回调接口+state
        String qrcodeUrl = String.format(WeChatConfig.getOpenQrcodeUrl(),weChatConfig.getOpenAppId(),callbackUrl,access_page);
        //返回微信登陆二维码的url，访问此url就会得到登陆二维码
        return JsonData.buildSuccess(qrcodeUrl);
    }

    /**
     * @description:  用户扫码确认登陆后，微信那边将会回调此接口(携带code)     如果用户是第一次扫描的话就相当于注册并登录了
     * @param: code  授权凭证
     * @param: state 用户点击登陆时所在页面，登陆完成后跳转回该页面
     * @param: response
     * @return: void
     */
    @GetMapping("callback")
    public void wechatUserCallback(@RequestParam(value = "code",required = true) String code, String state, HttpServletResponse response) throws IOException {

            //通过code获取access_token,然后通过access_token获取用户信息
            User user = userService.saveWechatUser(code);

            //生成token
            if (user != null){
                //生成jwt
                String token = JwtUtils.genJsonWebToken(user);

                //判断state是否带参数
                String sep_str = "?";
                if(state.indexOf("?") != -1) {
                    sep_str = "&";
                }


                // 跳转回state页面    需要拼接 http://  这样才不会站内跳转
                response.sendRedirect(state+sep_str+"token="+token+"&head_img="+user.getHeadImg()+"&name="+URLEncoder.encode(user.getName(), "UTF-8"));
            }
    }


    /**
     * 用户确认支付后，微信方回调此接口（post方式）并携带若干参数
     * 我方要进行校验签名，更新订单状态，并应答微信方
     */
    @RequestMapping("/order/callback")
    public void orderCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try(InputStream inputStream = request.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));){
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null){
                sb.append(line);
            }

            SortedMap<String,String> callbackMap = new TreeMap<>(WXPayUtils.xmlToMap(sb.toString()));
            System.out.println("微信支付回调参数："+callbackMap.toString());

            //校验签名是否正确
            if(WXPayUtils.checkSign(callbackMap, weChatConfig.getKey())){

                if("SUCCESS".equals(callbackMap.get("result_code"))){
                    String outTradeNo = callbackMap.get("out_trade_no");

                    VideoOrder dbVideoOrder = videoOrderService.findByOutTradeNo(outTradeNo);
                    System.out.println("订单号："+outTradeNo+"   支付状态："+dbVideoOrder.getState());


                    if(dbVideoOrder != null && dbVideoOrder.getState() == 0){
                        VideoOrder videoOrder = new VideoOrder();
                        videoOrder.setOpenid(callbackMap.get("openid"));
                        videoOrder.setOutTradeNo(outTradeNo);
                        videoOrder.setNotifyTime(new Date());
                        //把支付状态改为已支付（1）
                        videoOrder.setState(1);
                        int row = videoOrderService.updateVideoOrderByOutTradeNo(videoOrder);

                        if(row == 1){//通知微信订单处理成功
                            response.setContentType("text/xml");
                            response.getWriter().println("success");
                            return;
                        }
                    }


                }
            }

            //通知微信订单处理失败
            response.setContentType("text/xml");
            response.getWriter().println("fail");

        } catch (Exception e) {
            e.printStackTrace();
        }



    }



}

