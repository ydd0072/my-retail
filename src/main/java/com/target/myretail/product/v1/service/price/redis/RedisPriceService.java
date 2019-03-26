package com.target.myretail.product.v1.service.price.redis;

import com.target.myretail.product.v1.domain.Price;
import com.target.myretail.product.v1.service.price.PriceService;
import com.target.myretail.product.v1.service.price.redis.client.RedisPriceClient;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

@Service
public class RedisPriceService implements PriceService {

    private final RedisPriceClient redisPriceClient;

    public RedisPriceService(RedisPriceClient redisPriceClient) {
        Validate.notNull(redisPriceClient, "redisPriceClient cannot be null.");

        this.redisPriceClient = redisPriceClient;
    }

    @Override
    public Price getPrice(String productId) {
        Validate.notNull(productId, "productId cannot be null.");
        Price price = redisPriceClient.getPrice(productId);
        return price;
    }

    @Override
    public Price setPrice(String productId, Price price) {
        Validate.notNull(productId, "productId cannot be null.");
        Validate.notNull(price, "price cannot be null.");
        redisPriceClient.setPrice(productId, price);
        return price;
    }
}
