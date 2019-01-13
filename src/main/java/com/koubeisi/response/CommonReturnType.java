package com.koubeisi.response;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description
 * @Author Max
 * @Date 2019/1/13 22:47
 * @Version 1.0
 **/
public class CommonReturnType {

    //表示对应返回的请求结果，“fail”或者“success”
    private String status;
    //若“status”为“success”，则data类返回前端需要的data数据
    //若“status”为“fail”，则data类使用通用的错误码格式
    private Object data;


    //定义一个通用的方法
    public static CommonReturnType create(Object data){
        return CommonReturnType.create("success", data);
    }
    public static CommonReturnType create(String status, Object data){

        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus(status);
        commonReturnType.setData(data);

        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
