package com.koubeisi.dao;

import com.koubeisi.dataobject.UserPasswordDO;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPasswordDOMapperTest extends TestCase {

    @Autowired
    UserPasswordDOMapper userPasswordDOMapper;

//    @Test
    public void testInsert() {

        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setId(2);
        userPasswordDO.setEncrptPassword("dhafuelj3234");
        userPasswordDO.setUserId(1);

        int i = userPasswordDOMapper.insert(userPasswordDO);
        System.out.println(i);
    }

//    @Test
    public void testDeleteByPrimaryKey() {
        int i = userPasswordDOMapper.deleteByPrimaryKey(3);
        System.out.println(i);
    }

//    @Test
    public void testInsertSelective() {
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setId(3);
        userPasswordDO.setEncrptPassword("dh434532df34");
        userPasswordDO.setUserId(1);
        userPasswordDOMapper.insertSelective(userPasswordDO);
    }

//    @Test
    public void testSelectByPrimaryKey() {
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByPrimaryKey(1);
        System.out.println(userPasswordDO.getEncrptPassword() + "\t" + userPasswordDO.getUserId());
    }

    @Test
    public void testSelectByUserId() {
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(1);
        System.out.println(userPasswordDO.getEncrptPassword() + "\t" + userPasswordDO.getId());
    }

    public void testUpdateByPrimaryKeySelective() {
    }

//    @Test
    public void testUpdateByPrimaryKey() {
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setId(3);
        userPasswordDO.setEncrptPassword("3333333333");
        userPasswordDO.setUserId(1);
        int i = userPasswordDOMapper.updateByPrimaryKey(userPasswordDO);
        System.out.println(i);
    }
}