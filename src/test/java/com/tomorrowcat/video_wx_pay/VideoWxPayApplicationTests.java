package com.tomorrowcat.video_wx_pay;

import com.tomorrowcat.video_wx_pay.config.WeChatConfig;
import com.tomorrowcat.video_wx_pay.domain.User;
import com.tomorrowcat.video_wx_pay.utils.JwtUtils;
import com.tomorrowcat.video_wx_pay.utils.WXPayUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.SortedMap;
import java.util.TreeMap;

@SpringBootTest
class VideoWxPayApplicationTests {

    @Autowired
    private WeChatConfig wechatConfig;

    /**
     * @description:
     * @param:
     * @return: void
     */
    @Test
    void contextLoads() throws Exception {

        String payXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<xml>\n" +
                "<appid>wxk26lid61mte1z5a7</appid>\n" +
                "<body>汇编教程</body>\n" +
                "<mch_id>0582244336</mch_id>\n" +
                "<nonce_str>ae72b566d91747a694e39917870d23b1</nonce_str>\n" +
                "<notify_url>a2ynaw.natappfree.cc/api/v1/wechat/order/callback</notify_url>\n" +
                "<out_trade_no>cfbf189e6c5f413680b14ee141317aa0</out_trade_no>\n" +

                "<sign>FA1A590AA0F6B831396DED29FB5525A9</sign>\n" +

                "<spbill_create_ip>169.254.117.246</spbill_create_ip>\n" +
                "<total_fee>11</total_fee>\n" +
                "<trade_type>NATIVE</trade_type>\n" +
                "</xml>";

        boolean flag = WXPayUtils.checkSign(new TreeMap<String, String>(WXPayUtils.xmlToMap(payXml)),
                wechatConfig.getKey());

        System.out.println("签名校验结果=" + flag);
    }



}
