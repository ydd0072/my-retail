package com.target.myretail.product.v1.service.product.remote;

import com.target.myretail.product.v1.domain.Price;
import com.target.myretail.product.v1.domain.Product;
import com.target.myretail.product.v1.service.price.PriceService;
import com.target.myretail.product.v1.service.product.ProductService;
import com.target.myretail.product.v1.service.product.converter.RemoteResponseToProductConverter;
import com.target.myretail.product.v1.service.product.remote.client.RemoteProductClient;
import com.target.myretail.product.v1.service.product.response.ApiResponse;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

@Service
public class RemoteProductService implements ProductService {
    private final RemoteProductClient remoteProductClient;
    private final PriceService priceService;
    private final RemoteResponseToProductConverter remoteResponseToProductConverter;

    public RemoteProductService(RemoteProductClient remoteProductClient, PriceService priceService, RemoteResponseToProductConverter remoteResponseToProductConverter) {
        Validate.notNull(remoteProductClient, "remoteProductClient cannot be null.");
        Validate.notNull(priceService, "priceService cannot be null.");
        Validate.notNull(remoteResponseToProductConverter, "remoteResponseToProductConverter cannot be null.");
        this.remoteProductClient = remoteProductClient;
        this.priceService = priceService;
        this.remoteResponseToProductConverter = remoteResponseToProductConverter;
    }

    @Override
    public Product getProduct(String productId) {
        Product product;
        ApiResponse apiResponse = remoteProductClient.getProduct(productId);
        product = remoteResponseToProductConverter.convert(apiResponse);
        if (apiResponse != null && product != null) {
            Price price = priceService.getPrice(productId);
            product.setCurrent_price(price);
        }
        return product;
    }
}
