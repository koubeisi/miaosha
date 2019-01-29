package com.koubeisi.service.impl;

import com.koubeisi.error.BusinessException;
import com.koubeisi.service.OrderService;
import com.koubeisi.service.model.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() throws BusinessException {

        OrderModel orderModel= orderService.createOrder(1,17,2);

        System.out.println(orderModel.toString());
    }
}