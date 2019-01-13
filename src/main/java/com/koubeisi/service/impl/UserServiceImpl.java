package com.koubeisi.service.impl;

import com.koubeisi.dao.UserDOMapper;
import com.koubeisi.dao.UserPasswordDOMapper;
import com.koubeisi.dataobject.UserDO;
import com.koubeisi.dataobject.UserPasswordDO;
import com.koubeisi.service.UserService;
import com.koubeisi.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Max
 * @Date 2019/1/13 16:12
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserModelById(Integer id){

        //通过userDOMapper获取对应的UserDO
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);

        //通过用户Id获取对应的用户加密密码信息
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);

        //把UserDO和UserPasswordDO转换位UserModel
        return convertFromDataObject(userDO, userPasswordDO);
    }


    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){

        UserModel userModel = new UserModel();

        if (userDO == null){
            userModel = null;
        } else {
            BeanUtils.copyProperties(userDO, userModel);
        }
        if (userPasswordDO != null){
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }

        return userModel;
    }

}
