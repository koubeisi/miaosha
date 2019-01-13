package com.koubeisi.service.impl;


import com.koubeisi.service.UserService;
import com.koubeisi.service.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserById() {
        UserModel userModel = userService.getUserModelById(1);
        System.out.println(userModel.getName());
    }
}