package com.jxx.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.*;

import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class SimpleGateway {


    @Value("${entrance.key}")
    private String tokenHeader;

    @Bean
    public SimpleAuthFilter simpleAuthFilter() {
        return new SimpleAuthFilter();
    }
    @Bean
    public RouterFunction<ServerResponse> routeToApproval() {
        return RouterFunctions.route()
                .route(GatewayRequestPredicates.header("destination", "approval")
                        , http("http://localhost:8010"))
                .filter(LoggingFilter.log())
                .filter(simpleAuthFilter().execute(tokenHeader))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routeToVacations() {
        return RouterFunctions.route()
                .route(GatewayRequestPredicates.header("destination", "vacations")
                        , http("http://localhost:8090"))
                .filter(simpleAuthFilter().execute(tokenHeader))
                .build();
    }
}
