package com.koubeisi.dataobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDO {
    private String id;

    private Integer itemId;

    private Integer userId;

    private Double itemPrice;

    private Integer amount;

    private Double orderPrice;

}