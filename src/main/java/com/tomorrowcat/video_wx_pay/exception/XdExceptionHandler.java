package com.tomorrowcat.video_wx_pay.exception;

import com.tomorrowcat.video_wx_pay.domain.JsonData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理控制器
 */
@RestControllerAdvice
public class XdExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public JsonData Handler(Exception e){
        if(e instanceof XdException){
            XdException xdException = (XdException) e;
            return JsonData.buildError(xdException.getMsg(), xdException.getCode());

        }else {
            return JsonData.buildError("全局异常，未知错误");
        }

    }
}