package com.target.myretail.product.v1.domain;

import lombok.Data;

import javax.validation.Valid;

@Data
public class Product {
    private String id;
    @Valid
    private Price current_price;
    private String name;
}
