package com.jxx.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.UUID;

@Slf4j
public class LoggingFilter {

    public static HandlerFilterFunction<ServerResponse, ServerResponse> log() {
        return (request, next) -> {
            String requestId = UUID.randomUUID().toString().substring(0, 7);
            String uri = request.uri().toString();
            log.info("[{}][{}]",requestId, uri);
            ServerRequest serverRequest = ServerRequest.from(request).build();
            return next.handle(serverRequest);
        };
    }

}
