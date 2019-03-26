package com.target.myretail.product.v1.service.price.redis.client;

import com.target.myretail.product.v1.domain.Price;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class RedisPriceClient {
    private final static Logger logger = LoggerFactory.getLogger(RedisPriceClient.class);
    private static final String redisModifiableProductPriceKeyTemplate = "my-retail-blocking-api:product-price:product-id:%s";
    private final RedisTemplate<String, Price> redisTemplate;

    public RedisPriceClient(RedisTemplate<String, Price> redisTemplate) {
        Validate.notNull(redisTemplate, "redisTemplate cannot be null.");
        this.redisTemplate = redisTemplate;
    }

    public Price getPrice(String productId) {
        String redisKey = getKey(productId);
        try {
            return redisTemplate.opsForValue().get(redisKey);
        } catch (Exception e) {
            logger.error("Unable to get price from price data store for product : " + productId, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exception getting price.", e);
        }
    }

    public void setPrice(String productId, Price price) {
        Validate.notNull(productId, "productId cannot be null.");
        Validate.notNull(price, "price cannot be null.");
        try {
            redisTemplate.opsForValue().set(getKey(productId), price);
        } catch (Exception e) {
            logger.error("Unable to save price in price data store for product : " + productId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Exception updating price.", e);
        }
    }

    private static String getKey(String id) {
        Validate.notNull(id, "id cannot be null.");
        return String.format(redisModifiableProductPriceKeyTemplate,id);
    }
}
