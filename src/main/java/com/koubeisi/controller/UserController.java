package com.koubeisi.controller;

import com.koubeisi.dao.UserDOMapper;
import com.koubeisi.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author Max
 * @Date 2019年1月13日 13:26
 * @Version 1.0
 **/
@Controller
public class UserController {

    @Autowired
    private UserDOMapper userDOMapper;

    @GetMapping("/")
    @ResponseBody
    public String get(){

        UserDO userDO = userDOMapper.selectByPrimaryKey(1);

        if (userDO == null){
            return "用户对象不存在";
        }else{
            return userDO.toString();
        }

    }

}
