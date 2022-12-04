package com.example.spring.reactive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

/**
 * @author liuwenxue
 * @date 2022-11-19
 */
public class WebClientExample {
    private static Logger logger = LoggerFactory.getLogger(WebClientExample.class);

    public static ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }

    public static void main(String[] args) {
    }
}
