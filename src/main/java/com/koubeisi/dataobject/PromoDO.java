package com.koubeisi.dataobject;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PromoDO {

    private Integer id;

    private String name;

    private Date startTime;

    private Integer itemId;

    private Double promoItemPrice;

}