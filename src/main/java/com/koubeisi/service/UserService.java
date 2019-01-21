package com.koubeisi.service;

import com.koubeisi.error.BusinessException;
import com.koubeisi.service.model.UserModel;

public interface UserService {

    UserModel getUserModelById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    /**
     * @param telehpone         用户手机号
     * @param encrptPassword    加密后的密码信息
     * @throws BusinessException
     */
    UserModel validateLogin(String telehpone, String encrptPassword) throws BusinessException;
}
