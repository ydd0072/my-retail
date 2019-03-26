package com.target.myretail.product.v1.service.price;

import com.target.myretail.product.v1.domain.Price;

public interface PriceService {
    Price getPrice(String productId);
    Price setPrice(String id, Price price);
}
