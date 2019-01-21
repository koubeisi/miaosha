package com.koubeisi.service.impl;

import com.koubeisi.dao.UserDOMapper;
import com.koubeisi.dao.UserPasswordDOMapper;
import com.koubeisi.dataobject.UserDO;
import com.koubeisi.dataobject.UserPasswordDO;
import com.koubeisi.error.BusinessException;
import com.koubeisi.error.EnumBussinessError;
import com.koubeisi.service.UserService;
import com.koubeisi.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if (userModel == null){
            throw new BusinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR);
        }
        //如果需要的信息为空，则抛出异常
        if (StringUtils.isEmpty(userModel.getName())
                || userModel.getAge() == null
                || userModel.getGender() == null
                || StringUtils.isEmpty(userModel.getTelephone())
                || StringUtils.isEmpty(userModel.getEncrptPassword())) {
            throw new BusinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR);
        }

        UserDO userDO = convertFromModel(userModel);
        //在UserDOMapper.xml映射文件中设置属性keyProperty="id" useGeneratedKeys="true"
        //无需重新查询，生成的id的值会自动设置到userDO中
        try {
            userDOMapper.insertSelective(userDO);       //1.此时，userDO的id属性为null
        } catch (DuplicateKeyException exception) {
            throw new BusinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR,"手机号已经注册");
        }
        //因此，在此可以直接调用其id
        userModel.setId(userDO.getId());                //2.此时，userDO的id属性已经生成

        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);

        userPasswordDOMapper.insertSelective(userPasswordDO);

    }

    @Override
    public UserModel validateLogin(String telehpone, String encrptPassword) throws BusinessException {

        //通过手机号获取数据库中用户信息
        UserDO userDO = userDOMapper.selectByTelephone(telehpone);
        if (userDO == null) {
            throw new BusinessException(EnumBussinessError.USER_LOGIN_FAIL);
        }

        //通过用户id获取数据库中用户密码信息
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO,userPasswordDO);

        //对比密码是否正确
        if (!StringUtils.equals(userPasswordDO.getEncrptPassword(), encrptPassword)) {
            throw new BusinessException(EnumBussinessError.USER_LOGIN_FAIL);
        }

        return userModel;
    }

    private UserDO convertFromModel(UserModel userModel) {

        if (userModel == null) {
            return null;
        }

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel,userDO);

        return userDO;
    }

    private UserPasswordDO convertPasswordFromModel(UserModel userModel) {

        if (userModel == null) {
            return null;
        }

        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setUserId(userModel.getId());
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());

        return userPasswordDO;
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
