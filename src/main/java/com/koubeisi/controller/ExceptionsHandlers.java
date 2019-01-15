package com.koubeisi.controller;

import com.koubeisi.error.BusinessException;
import com.koubeisi.error.EnumBussinessError;
import com.koubeisi.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/14 19:07
 * @Version 1.0
 **/
@ControllerAdvice
public class ExceptionsHandlers {

    //定义@ExceptionHandler解决未被controller层捕获的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK, reason = "捕获的异常")
    @ResponseBody
    public CommonReturnType handlerException(Exception exception){

        CommonReturnType commonReturnType = new CommonReturnType();
        Map<String, Object> responseData = new HashMap();

        if (exception instanceof BusinessException) {
            responseData.put("errorCode",((BusinessException)exception).getErrorCode());
            responseData.put("errorMsg",((BusinessException)exception).getErrorMsg());

            commonReturnType.setStatus("fail");
            commonReturnType.setData(responseData);
        } else {
            responseData.put("errorCode", EnumBussinessError.UNKNOW_ERROR.getErrorCode());
            responseData.put("errorMsg",EnumBussinessError.UNKNOW_ERROR.getErrorMsg());

            commonReturnType.setStatus("fail");
            commonReturnType.setData(responseData);
        }

        return commonReturnType;
    }

}
