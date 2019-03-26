package com.target.myretail.product.v1.service.product.converter;

import com.target.myretail.product.v1.domain.Product;
import com.target.myretail.product.v1.service.product.response.ApiResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RemoteResponseToProductConverter implements Converter<ApiResponse, Product> {
    @Override
    public Product convert(ApiResponse apiResponse) {
        Product product = new Product();
        if (apiResponse == null) {
            return product;
        }
        product.setId(apiResponse.getProductResponse().getItemResponse().getId());
        product.setName(apiResponse.getProductResponse().getItemResponse().getProductDetail().getTitle());
        return product;
    }
}
