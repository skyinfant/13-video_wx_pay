package com.tomorrowcat.video_wx_pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement   //开启事务
@MapperScan("com.tomorrowcat.video_wx_pay.mapper")
public class VideoWxPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoWxPayApplication.class, args);
    }

}
