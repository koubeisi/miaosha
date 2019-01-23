package com.koubeisi.validate;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/21 21:52
 * @Version 1.0
 **/
public class ValidatorResult {

    private boolean hasError = false;

    private Map<String, String> errorMsgMap = new HashMap<>();

    public boolean isHasError() {
        return this.hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public boolean getHasError() {
        return this.hasError;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    public Map<String, String> getErrorMsgMap() {
        return this.errorMsgMap;
    }

    public String getErrorMsg() {
        return StringUtils.join(errorMsgMap.values().iterator(), ',');
    }
}
