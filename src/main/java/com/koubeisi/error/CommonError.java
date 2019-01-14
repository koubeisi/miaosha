package com.koubeisi.error;

public interface CommonError {

    Integer getErrorCode();
    String getErrorMsg();
    CommonError setErrorMsg(String errorMsg);
}
