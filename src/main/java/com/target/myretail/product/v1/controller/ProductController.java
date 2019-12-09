package com.target.myretail.product.v1.controller;

import com.target.myretail.ProductVersion;
import com.target.myretail.product.v1.domain.Product;
import com.target.myretail.product.v1.service.price.PriceService;
import com.target.myretail.product.v1.service.product.ProductService;
import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/products", produces = {ProductVersion.DEFAULT, ProductVersion.V1})
public class ProductController {
    private final ProductService productService;
    private final PriceService priceService;

    public ProductController(ProductService productService, PriceService priceService) {
        Validate.notNull(productService, "productService cannot be null.");
        Validate.notNull(priceService, "priceService cannot be null.");

        this.productService = productService;
        this.priceService = priceService;
    }

    @ResponseBody
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product getProduct(
            @PathVariable String id
    ) {
        return productService.getProduct(id);
    }

    @ResponseBody
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Product setProductPrice(
            @PathVariable String id,
            @Valid @RequestBody Product productRequest
    ) {
        Product product = productService.getProduct(id);
       if (product != null) {
           priceService.setPrice(id, productRequest.getCurrent_price());
           product.setCurrent_price(productRequest.getCurrent_price());
       }
       return product;
    }
}
