package com.tomorrowcat.video_wx_pay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: kim
 * @create: 2021-09-17 11:07
 * @version: 1.0.0
 */
@RestController
public class TestController {

    @RequestMapping("test")
    public Object test(){
        return "i love you";
    }
}