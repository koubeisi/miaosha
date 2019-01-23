package com.koubeisi.controller.viewobject;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/23 14:55
 * @Version 1.0
 **/
@Getter
@Setter
public class ItemVO {

    private Integer id;

    //商品名称
    private String title;

    //商品价格

    private BigDecimal price;

    //商品库存
    private Integer stock;

    //商品描述
    private String description;

    //商品的销量
    private Integer sales;

    //商品描述图片的url
    private String imgUrl;
}
