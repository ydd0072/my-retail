package com.target.myretail.product.v1.service.product

import com.target.myretail.product.v1.domain.Price
import com.target.myretail.product.v1.domain.Product
import com.target.myretail.product.v1.service.price.PriceService
import com.target.myretail.product.v1.service.price.redis.RedisPriceService
import com.target.myretail.product.v1.service.product.converter.RemoteResponseToProductConverter
import com.target.myretail.product.v1.service.product.remote.RemoteProductService
import com.target.myretail.product.v1.service.product.remote.client.RemoteProductClient
import com.target.myretail.product.v1.service.product.response.ApiResponse
import spock.lang.Specification
import spock.lang.Unroll

class RemoteProductServiceSpec extends Specification {
    RemoteProductService remoteProductService
    RemoteProductClient remoteProductClient = Mock(RemoteProductClient)
    PriceService priceService = Mock(RedisPriceService)
    RemoteResponseToProductConverter remoteResponseToProductConverter = Mock(RemoteResponseToProductConverter)

    def setup() {
        remoteProductService = new RemoteProductService(remoteProductClient, priceService, remoteResponseToProductConverter)
    }

    @Unroll
    void 'constructor - #message'() {
        when:
        new RemoteProductService(argProductClient, argPriceService, argRemoteResponseToProductConverter)

        then:
        NullPointerException e = thrown(NullPointerException)
        e.message == message

        where:
        message                                           | argProductClient           | argPriceService         | argRemoteResponseToProductConverter
        'remoteProductClient cannot be null.'             | null                       | Mock(RedisPriceService) | Mock(RemoteResponseToProductConverter)
        'priceService cannot be null.'                    | Mock(RemoteProductClient) | null                    | Mock(RemoteResponseToProductConverter)
        'remoteResponseToProductConverter cannot be null.' | Mock(RemoteProductClient) | Mock(RedisPriceService) | null
    }

    def "Get Price"() {
        given:

        when:
        remoteProductService.getProduct('13860428')

        then:
        1 * remoteProductClient.getProduct('13860428') >> new ApiResponse()
        1 * remoteResponseToProductConverter.convert(_) >> new Product()
        1 * priceService.getPrice('13860428') >> new Price()
    }
}
