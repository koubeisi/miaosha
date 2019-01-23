package com.koubeisi.dao;

import com.koubeisi.dataobject.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDOMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDOMapperTest.class);

    @Autowired
    UserDOMapper userDOMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {

        UserDO userDO = new UserDO();
        userDO.setName("寇倍思");
        userDO.setAge(27);
        userDO.setGender(new Byte("1"));
        userDO.setRegistreMode("byphone");
        userDO.setTelephone("12345678901");

        int i = userDOMapper.insertSelective(userDO);

        LOGGER.info("{}",i);
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}