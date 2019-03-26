package com.target.myretail;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HomeController {

    private final HomeRootResourceAssembler homeRootResourceAssembler;

    public HomeController(HomeRootResourceAssembler homeRootResourceAssembler) {
        this.homeRootResourceAssembler = homeRootResourceAssembler;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public Resource<String> getRoot() {
        return homeRootResourceAssembler.toResource("Root endpoint, links for supported endpoints in _links.");
    }

    @ResponseBody
    @RequestMapping(value = "/swagger-ui.html", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public Resource<String> getSwaggerUi() {
        return homeRootResourceAssembler.toResource("Swagger ui endpoint.");
    }
}
