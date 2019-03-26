package com.target.myretail.product.v1.service.price

import com.target.myretail.product.v1.domain.Price
import com.target.myretail.product.v1.service.price.redis.RedisPriceService
import com.target.myretail.product.v1.service.price.redis.client.RedisPriceClient
import spock.lang.Specification

class RedisPriceServiceSpec extends Specification {
    RedisPriceService redisPriceService
    RedisPriceClient redisPriceClient = Mock(RedisPriceClient)

    def setup() {
        redisPriceService = new RedisPriceService(redisPriceClient)
    }

    void 'constructor - #message'() {
        when:
        new RedisPriceService(null)

        then:
        NullPointerException e = thrown(NullPointerException)
    }

    def "Get Price"() {
        when:
        redisPriceService.getPrice('13860428')

        then:
        1 * redisPriceClient.getPrice('13860428') >> new Price()
    }

    def "Get Price on NULL Product"() {
        when:
        redisPriceService.getPrice(null)

        then:
        thrown(NullPointerException)
    }

    def "set Price"() {
        given:
        Price price = new Price()
        price.setValue(new BigDecimal(99.99))
        price.setCurrencyCode('USD')

        when:
        redisPriceService.setPrice('13860428', price)

        then:
        1 * redisPriceClient.setPrice('13860428', price)
    }

    def "Set Price on NULL Product Id and NULL Price"() {
        when:
        redisPriceService.setPrice(null, null)

        then:
        thrown(NullPointerException)
    }
}
