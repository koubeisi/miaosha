package com.koubeisi.controller;

import com.koubeisi.controller.viewobject.UserVO;
import com.koubeisi.error.BusinessException;
import com.koubeisi.error.EnumBussinessError;
import com.koubeisi.response.CommonReturnType;
import com.koubeisi.service.UserService;
import com.koubeisi.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


/**
 * @Description
 * @Author koubeisi
 * @Date 2019年1月13日 13:26
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    //用户注册接口
    @PostMapping("/register")
    public CommonReturnType register(@RequestParam(value = "telephone") String telephone,
                                     @RequestParam(value = "otpCode") String otpCode,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "gender") Byte gender,
                                     @RequestParam(value = "age") Integer age) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号与对应的otpCode相符合
        String inSessionOtpCode = (String) request.getSession().getAttribute(telephone);
        if (!StringUtils.equals(otpCode, inSessionOtpCode)) {
            throw new BusinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR, "短信验证码不符合");
        }
        //用户注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setGender(gender);
        userModel.setTelephone(telephone);
        userModel.setRegistreMode("byphone");
        userModel.setEncrptPassword(this.encodeByMD5(password));
        userService.register(userModel);

        return CommonReturnType.create(null);
    }

    private String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符串
        String newStr = base64Encoder.encode(md5.digest(str.getBytes("UTF-8")));

        return newStr;
    }

    @PostMapping("/login")
    public CommonReturnType login(@RequestParam(value = "telephone") String telephone,
                                  @RequestParam(value = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if (StringUtils.isEmpty(telephone) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR);
        }

        //用户登录校验，校验登录是否合法
        UserModel userModel = userService.validateLogin(telephone, this.encodeByMD5(password));

        //将登录凭证加入到用户登录成功的session内
        request.getSession().setAttribute("IS_LOGIN", true);
        request.getSession().setAttribute("LOGIN_USER", userModel);

        return CommonReturnType.create(null);

    }


    @PostMapping(value = "/getotp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    //@CrossOrigin    //该注解使得此方法支持跨域请求
    public CommonReturnType getOTP(@RequestParam(name = "telephone") String telephone) {

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
    public CommonReturnType get(@RequestParam(name = "id") Integer id) throws BusinessException {

        UserModel userModel = userService.getUserModelById(id);

        if (userModel == null) {
//            userModel.setEncrptPassword("123");
            throw new BusinessException(EnumBussinessError.USER_NOT_EXIST);
        }

        UserVO userVO = convertFromUserModel(userModel);

        //返回通用对象
        CommonReturnType commonReturnType;
        if (userVO != null) {

            commonReturnType = CommonReturnType.create(userVO);
        } else {
            commonReturnType = null;
        }

        return commonReturnType;
    }

    private UserVO convertFromUserModel(UserModel userModel) {

        UserVO userVO = new UserVO();

        if (userModel != null) {
            BeanUtils.copyProperties(userModel, userVO);
        } else {
            userVO = null;
        }

        return userVO;
    }


}
