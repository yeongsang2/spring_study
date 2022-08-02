package com.jpabook.jpashop.service;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateItemDto {

    public UpdateItemDto(int price, String name, int stockQuantity) {
        this.price = price;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    private int price;
    private String name;
    private int stockQuantity;
}
