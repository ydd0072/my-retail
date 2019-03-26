package com.target.myretail.product.v1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class Price {
    @JsonProperty("currency_code")
    @NotNull(message = "current_code is required")
    private String currencyCode;

    @Valid
    @NotNull(message = "price is required")
    @DecimalMin(value = "0.00", message = "price must be 0.00 or greater")
    private BigDecimal value;
}
