package com.target.myretail.config.rest;

import com.target.myretail.config.property.rest.HttpClientConfiguration;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplatesConfig {

    @Bean
    public RestTemplate myRetailServiceTemplate(HttpClientConfiguration httpClientConfiguration) {
        return configureRestTemplate(httpClientConfiguration);
    }

    private RestTemplate configureRestTemplate(HttpClientConfiguration clientConfig) {
        int readTimeout = clientConfig.getReadTimeout().isPresent() ? clientConfig.getReadTimeout().get() : -1;
        int connectTimeout = clientConfig.getConnectTimeout().isPresent() ? clientConfig.getConnectTimeout().get() : -1;

        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(60, TimeUnit.SECONDS);
        poolingHttpClientConnectionManager.setMaxTotal(200);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                .setConnectionManager(poolingHttpClientConnectionManager)
                .useSystemProperties()
                .build();

        ClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplateBuilder()
                .requestFactory(() -> clientHttpRequestFactory)
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }
}
