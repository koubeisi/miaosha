package com.koubeisi.service;

import com.koubeisi.error.BusinessException;
import com.koubeisi.service.model.OrderModel;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/24 16:38
 * @Version 1.0
 **/
public interface OrderService {

    OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException;
}
