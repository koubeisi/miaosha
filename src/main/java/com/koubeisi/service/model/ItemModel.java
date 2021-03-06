package com.koubeisi.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/22 20:11
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
public class ItemModel {

    private Integer id;

    //商品名称
    @NotBlank(message = "商品名不能为空")
    private String title;

    //商品价格
    @NotNull(message = "价格价格不能为空")
    @Min(value = 0, message = "商品价格必须大于零")
    private BigDecimal price;

    //商品库存
    @NotNull(message = "商品库存不能为空")
    private Integer stock;

    //商品描述
    @NotBlank(message = "商品描述不能为空")
    private String description;

    //商品的销量
    private Integer sales;

    //商品描述图片的url
    @NotBlank(message = "商品图片信息不能为空")
    private String imgUrl;

}
