package com.tomorrowcat.video_wx_pay.service;

import com.tomorrowcat.video_wx_pay.domain.User;

/**
 * @description:
 * @author: kim
 * @create: 2021-06-29 22:04
 * @version: 1.0.0
 */
public interface UserService {

    User saveWechatUser(String code);
}