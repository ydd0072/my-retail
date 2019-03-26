package com.target.myretail.product.v1.service.product.remote.client;

import com.target.myretail.product.v1.service.product.response.ApiResponse;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class RemoteProductClient {
    private final Logger logger = LoggerFactory.getLogger(RemoteProductClient.class);

    private final RestTemplate restTemplate;

    @Value("${product.api.url.prefix}")
    String prefixedApiUrl;

    @Value("${product.api.url.suffix}")
    String suffixApiUrl;

    public RemoteProductClient(RestTemplate restTemplate) {
        Validate.notNull(restTemplate, "restTemplate cannot be null.");
        this.restTemplate = restTemplate;
    }

    @Cacheable("product")
    public ApiResponse getProduct(String productId) throws ResponseStatusException {
        Validate.notBlank(productId, "productId cannot be blank.");
        ApiResponse apiResponse = null;
        try {
            apiResponse = restTemplate.getForObject(getApiUrl(productId), ApiResponse.class);
        } catch (HttpStatusCodeException hsce) {
            logger.error("HttpStatusCodeException calling product api on product : " + productId , hsce);
            if (hsce.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product Not Found", hsce);
            }
        } catch (Exception e) {
            logger.error("Exception calling product api for product : " + productId, e);
            throw new ResponseStatusException (HttpStatus.INTERNAL_SERVER_ERROR, "Exception calling product api.", e);
        }
        return apiResponse;
    }

    private String getApiUrl(String productId) {
        return prefixedApiUrl + productId + suffixApiUrl;
    }
}
