package com.koubeisi.service;

import com.koubeisi.error.BusinessException;
import com.koubeisi.service.model.UserModel;

public interface UserService {

    UserModel getUserModelById(Integer id);

    void register(UserModel userModel) throws BusinessException;
}
