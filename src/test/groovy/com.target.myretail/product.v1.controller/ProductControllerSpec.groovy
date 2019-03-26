package com.target.myretail.product.v1.controller

import com.target.myretail.product.v1.domain.Price
import com.target.myretail.product.v1.domain.Product
import com.target.myretail.product.v1.service.price.PriceService
import com.target.myretail.product.v1.service.price.redis.RedisPriceService
import com.target.myretail.product.v1.service.product.ProductService
import com.target.myretail.product.v1.service.product.remote.RemoteProductService
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import spock.lang.Specification
import spock.lang.Unroll

class ProductControllerSpec extends Specification {
    ProductController productController
    ProductService productService = Mock(RemoteProductService)
    PriceService priceService = Mock(RedisPriceService)

    def setup() {
        MockHttpServletRequest req = new MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(req))
        productController = new ProductController(productService, priceService)
    }

    @Unroll
    void 'constructor - #message'() {
        when:
        new ProductController(argProductService, argPriceService)

        then:
        NullPointerException e = thrown(NullPointerException)
        e.message == message

        where:
        message                                | argProductService          | argPriceService
        'productService cannot be null.'       | null                       | Mock(RedisPriceService)
        'priceService cannot be null.'         | Mock(RemoteProductService) | null
    }

    def "Get Product"() {
        given:
        Product product = new Product()
        product.setName('The Big Lebowski (Blu-ray)')
        product.setId('13860428')
        Price price = new Price()
        price.setCurrencyCode('USD')
        price.setValue(new BigDecimal(99))
        product.setCurrent_price(price)
        productService.getProduct('13860428') >> product

        when:
        productController.getProduct('13860428')

        then:
        1 * productService.getProduct('13860428')


    }

    def "Get Price"() {
        given:
        Product product = new Product()
        product.setName('The Big Lebowski (Blu-ray)')
        product.setId('13860428')
        Price price = new Price()
        price.setCurrencyCode('USD')
        price.setValue(new BigDecimal(99))
        product.setCurrent_price(price)

        when:
        productController.updateProductPrice('13860428', product)

        then:
        1 * productService.getProduct('13860428') >> product
        1 * priceService.setPrice('13860428', product.getCurrent_price())


    }

}
