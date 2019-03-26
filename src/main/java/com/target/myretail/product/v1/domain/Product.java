package com.target.myretail.product.v1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

@NoArgsConstructor
@Getter
@Setter
public class Product {
    private String id;
    @Valid
    private Price current_price;
    private String name;
}
