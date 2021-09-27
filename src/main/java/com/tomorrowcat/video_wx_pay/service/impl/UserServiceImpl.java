package com.tomorrowcat.video_wx_pay.service.impl;

import com.tomorrowcat.video_wx_pay.config.WeChatConfig;
import com.tomorrowcat.video_wx_pay.domain.User;
import com.tomorrowcat.video_wx_pay.mapper.UserMapper;
import com.tomorrowcat.video_wx_pay.service.UserService;
import com.tomorrowcat.video_wx_pay.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: kim
 * @create: 2021-06-29 22:05
 * @version: 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserMapper userMapper;
    
    /**
     * @description: 通过code获取access_token,再通过token获取用户信息
     * @param: code  授权凭证
     * @return: User
     */
    @Override
    public User saveWechatUser(String code) {
        //通过这个地址获取access_token
        String accessTokenUrl = String.format(WeChatConfig.getOpenAccessTokenUrl(),weChatConfig.getOpenAppId(),weChatConfig.getOpenAppsecret(),code);

        //获取access_token
        Map<String ,Object> baseMap = HttpUtils.doGet(accessTokenUrl);

        if(baseMap == null || baseMap.isEmpty()){
            return null;
        }

        String accessToken = (String) baseMap.get("access_token");

        String openId  = (String) baseMap.get("openid");

        User dbUser = userMapper.findByOpenid(openId);

        //老用户
        if(dbUser != null){
            return dbUser;
        }


        //新用户
        //获取用户基本信息
        String userInfoUrl = String.format(WeChatConfig.getOpenUserInfoUrl(),accessToken,openId);

        Map<String ,Object> baseUserMap = HttpUtils.doGet(userInfoUrl);

        if(baseUserMap == null || baseUserMap.isEmpty()){ return  null; }

        String nickname = (String)baseUserMap.get("nickname");
        Double tempSex  = (Double) baseUserMap.get("sex");
        int sex = tempSex.intValue();
        String province = (String)baseUserMap.get("province");
        String city = (String)baseUserMap.get("city");
        String country = (String)baseUserMap.get("country");
        String headimgurl = (String)baseUserMap.get("headimgurl");
        StringBuilder sb = new StringBuilder(country).append("||").append(province).append("||").append(city);
        String finalAdrress = sb.toString();
        try{
            //解决乱码
            nickname  = new String(nickname.getBytes("ISO-8859-1"),"UTF-8");
            finalAdrress  = new String(finalAdrress.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCity(finalAdrress);
        user.setCreateTime(new Date());
        //新用户入库
        userMapper.save(user);
        return user;

    }
}

//class  Test{
//    public static void main(String[] args) throws UnsupportedEncodingException {
//        System.out.println(URLDecoder.decode("https%3A%2F%2Fxdclass.net%2F%23%2Fcoursedetail%3Fvideo_id%3D64%26login_type%3Dpc", "GBK"));
//    }
//}
















