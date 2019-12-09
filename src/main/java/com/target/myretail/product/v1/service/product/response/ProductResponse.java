package com.target.myretail.product.v1.service.product.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductResponse {
    @JsonProperty("item")
    private ItemResponse itemResponse;
}
