package com.target.myretail.config.property.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HttpClientConfiguration {

    @Autowired
    private Environment properties;

    public Optional<Integer> getConnectTimeout() {
        String connectTimeout = properties.getProperty(String.format("com.target.myretail.rest.client.myretail.service.connect.timeout"));
        return toOptionalInt(connectTimeout);
    }
    public Optional<Integer> getReadTimeout() {
        String readTimeout = properties.getProperty(String.format("com.target.myretail.rest.client.myretail.service.read.timeout"));
        return toOptionalInt(readTimeout);
    }

    private static Optional<Integer> toOptionalInt(String value) {
        if (StringUtils.isEmpty(value)) {
            return Optional.empty();
        }

        return Optional.of(Integer.valueOf(value));
    }
}
