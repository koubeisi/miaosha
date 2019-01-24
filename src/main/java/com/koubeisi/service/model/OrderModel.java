package com.koubeisi.service.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/24 16:10
 * @Version 1.0
 **/
@Getter
@Setter
public class OrderModel {

    //2019012400012828
    private String id;

    //购买用户的id
    private Integer userId;

    //购买的用户id
    private Integer itemId;

    //购买商品的单价==购买当时商品的价格
    private BigDecimal itemPrice;

    //购买数量
    private Integer amount;

    //购买金额
    private BigDecimal orderPrice;
}
