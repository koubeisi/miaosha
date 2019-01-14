package com.koubeisi.error;

/**
 * @Description 包装类业务异常实现
 * @Author Max
 * @Date 2019/1/14 10:52
 * @Version 1.0
 **/
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    //直接接受EmunBunisessError的传参用于构造业务异常
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    //接收自定义errorMsg的方式构造业务异常
    public BusinessException(CommonError commonError, String errorMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(errorMsg);
    }


    @Override
    public Integer getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.commonError.setErrorMsg(errorMsg);
        return this;
    }
}
