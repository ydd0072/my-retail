package com.target.myretail.product.v1.service.product.converter


import com.target.myretail.product.v1.domain.Product
import com.target.myretail.product.v1.service.product.response.ApiResponse
import com.target.myretail.product.v1.service.product.response.ItemResponse
import com.target.myretail.product.v1.service.product.response.ProductDetail
import com.target.myretail.product.v1.service.product.response.ProductResponse
import spock.lang.Specification

class RemoteResponseToProductConverterSpec extends Specification {
    RemoteResponseToProductConverter remoteResponseToProductConverter;
    def setup () {
        this.remoteResponseToProductConverter = new RemoteResponseToProductConverter()
    }

    def "Get Product"() {
        given:
        ProductDetail productDetail = new ProductDetail(title: "title")
        ItemResponse itemResponse = new ItemResponse(
                id: "id",
                productDetail: productDetail
        )
        ProductResponse productResponse = new ProductResponse(itemResponse: itemResponse)
        ApiResponse apiResponse = new ApiResponse(productResponse: productResponse)

        when:
        Product product = remoteResponseToProductConverter.convert(apiResponse)

        then:
        product
        product.name
        product.name == apiResponse.productResponse.itemResponse.productDetail.title
        product.id == apiResponse.productResponse.itemResponse.id
    }

    def "Get Empty Product"() {
        when:
        Product product = remoteResponseToProductConverter.convert(null)

        then:
        product
        !product.name
    }
}
