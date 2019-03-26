package com.target.myretail.product.v1.service.product.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ItemResponse {
    @JsonProperty("tcin")
    private String id;
    @JsonProperty("product_description")
    private ProductDetail productDetail;
}
