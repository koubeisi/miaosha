package com.koubeisi.controller;

import com.koubeisi.controller.viewobject.OrderVO;
import com.koubeisi.error.BusinessException;
import com.koubeisi.error.EnumBussinessError;
import com.koubeisi.response.CommonReturnType;
import com.koubeisi.service.OrderService;
import com.koubeisi.service.model.OrderModel;
import com.koubeisi.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/29 9:19
 * @Version 1.0
 **/
@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping(path = "/create")
    public CommonReturnType createOrder(@RequestParam(value = "itemId") Integer itemId,
                                        @RequestParam(value = "amount") Integer amount) throws BusinessException {

        Boolean isLogiin=(Boolean)request.getSession().getAttribute("IS_LOGIN");
        if (isLogiin ==null||!isLogiin.booleanValue()) {
            throw new BusinessException(EnumBussinessError.USER_NOT_LOGIN);
        }

        UserModel userModel=(UserModel) request.getSession().getAttribute("LOGIN_USER");

        OrderModel orderModel=orderService.createOrder(userModel.getId(),itemId,amount);


        return CommonReturnType.create(convertOrderVOFromOrderModel(orderModel));
    }

    private OrderVO convertOrderVOFromOrderModel(OrderModel orderModel){

        OrderVO orderVO =new OrderVO();
        BeanUtils.copyProperties(orderModel,orderVO);

        return orderVO;
    }
}
