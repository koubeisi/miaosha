package com.koubeisi.controller;

import com.koubeisi.controller.viewobject.UserVO;
import com.koubeisi.error.BusinessException;
import com.koubeisi.error.EnumBussinessError;
import com.koubeisi.response.CommonReturnType;
import com.koubeisi.service.UserService;
import com.koubeisi.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author Max
 * @Date 2019年1月13日 13:26
 * @Version 1.0
 **/
@Controller("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    @ResponseBody
    public CommonReturnType get(@RequestParam(name="id") Integer id) throws BusinessException {

        UserModel userModel = userService.getUserModelById(id);

        if (userModel == null) {
            throw new BusinessException(EnumBussinessError.USER_NOT_EXIST);
        }

        UserVO userVO = convertFromUserModel(userModel);

        //返回通用对象
        CommonReturnType commonReturnType;
        if (userVO != null){

            commonReturnType = CommonReturnType.create(userVO);
        } else {
            commonReturnType = null;
        }

        return commonReturnType;
    }

    private UserVO convertFromUserModel(UserModel userModel){

        UserVO userVO = new UserVO();

        if (userModel != null){
            BeanUtils.copyProperties(userModel, userVO);
        } else {
            userVO = null;
        }

        return userVO;
    }

}
