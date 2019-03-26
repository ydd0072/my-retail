package com.target.myretail;

import com.target.myretail.product.v1.controller.ProductController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class HomeRootResourceAssembler extends ResourceAssemblerSupport<String, Resource> {

    public HomeRootResourceAssembler() {
        super(HomeController.class, Resource.class);
    }

    @Override
    public Resource<String> toResource(String message) {

        Resource<String> resource = new Resource<>(message);

        resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(HomeController.class).getRoot()).withSelfRel());
        resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ProductController.class).getProduct("13860428")).withRel("example-uri"));
        resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(HomeController.class).getSwaggerUi()).withRel("swagger-ui"));

        return resource;
    }
}
