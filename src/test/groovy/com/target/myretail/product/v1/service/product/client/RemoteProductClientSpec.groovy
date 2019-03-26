package com.target.myretail.product.v1.service.product.client

import com.target.myretail.product.v1.service.product.remote.client.RemoteProductClient
import com.target.myretail.product.v1.service.product.response.ApiResponse
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

class RemoteProductClientSpec extends Specification {
    RemoteProductClient remoteProductClient
    RestTemplate restTemplate = Mock(RestTemplate)

    def setup() {
        this.remoteProductClient = new RemoteProductClient(restTemplate)
    }

    void 'constructor - #message'() {
        when:
        new RemoteProductClient(null)

        then:
        NullPointerException e = thrown(NullPointerException)
    }

    def "Get Product"() {
        given:
        restTemplate.getForObject(_, ApiResponse) >> new Exception()

        when:
        remoteProductClient.getProduct('13860428')

        then:
        thrown(ResponseStatusException)
    }

    def "Get Product ok"() {
        when:
        remoteProductClient.getProduct('13860428')

        then:
        1 * restTemplate.getForObject(_, ApiResponse) >> new ApiResponse()
    }
}
