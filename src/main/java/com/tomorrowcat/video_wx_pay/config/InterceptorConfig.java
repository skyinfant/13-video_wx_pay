package com.tomorrowcat.video_wx_pay.config;

import com.tomorrowcat.video_wx_pay.interceptor.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:拦截器配置
 * @author: kim
 * @create: 2021-06-30 22:22
 * @version: 1.0.0
 */
//@Configuration        //因为微信登陆有问题，所以先注释登陆拦截，否则无法扫码支付
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/user/api/v1/*/**");
        WebMvcConfigurer.super.addInterceptors(registry);

    }
}