package com.koubeisi.controller;

import com.koubeisi.controller.viewobject.UserVO;
import com.koubeisi.error.BusinessException;
import com.koubeisi.error.EnumBussinessError;
import com.koubeisi.response.CommonReturnType;
import com.koubeisi.service.UserService;
import com.koubeisi.service.model.UserModel;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;


/**
 * @Description
 * @Author koubeisi
 * @Date 2019年1月13日 13:26
 * @Version 1.0
 **/
@RestController("user")
@RequestMapping("/user")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/getotp")
    public CommonReturnType getOTP(@RequestParam(name="telephone") String telephone) {

        //需要按照一定的规则生成OTP验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt = randomInt + 10000;
        String otpCode = String.valueOf(randomInt);

        //将OTP验证码与对应的手机号管理（一般选择Redis），这里使用session
        request.getSession().setAttribute(telephone, otpCode);

        //将OTP验证码通过短信通道发送给用户，省略
        LOGGER.info("telephone=" + telephone + "&otpCode=" + otpCode);

        return CommonReturnType.create(null);
    }

    @GetMapping("/get")
    public CommonReturnType get(@RequestParam(name="id") Integer id) throws BusinessException {

        UserModel userModel = userService.getUserModelById(id);

        if (userModel == null) {
//            userModel.setEncrptPassword("123");
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
