package com.target.myretail.product.v1.service.product.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ApiResponse {
    @JsonProperty("product")
    private ProductResponse productResponse;
}
