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
import org.springframework.web.bind.annotation.*;


/**
 * @Description
 * @Author koubeisi
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
            userModel.setEncrptPassword("123");
//            throw new BusinessException(EnumBussinessError.USER_NOT_EXIST);
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

    //定义@ExceptionHandler解决未被controller层捕获的exception
//    @ExceptionHandler(value=Exception.class)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public Object handlerException(HttpServletRequest request, Exception exception){
//
//        CommonReturnType commonReturnType = new CommonReturnType();
//        commonReturnType.setStatus("fail");
//        commonReturnType.setData(exception);
//
//        return commonReturnType;
//    }

}
