package com.koubeisi.service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/30 0:48
 * @Version 1.0
 **/
public class PromoModel {

    private Integer id;

    //秒杀活动名称
    private String promoName;

    //秒杀开始时间
    private LocalDateTime startData;

    //秒杀商品id
    private Integer itemId;

    //秒杀商品的价格
    private BigDecimal promoItemPrice;

}
