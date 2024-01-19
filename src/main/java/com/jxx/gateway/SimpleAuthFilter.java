package com.jxx.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

/**
 * https://docs.spring.io/spring-cloud-gateway/reference/spring-cloud-gateway-server-mvc/filters/tokenrelay.html
 * 위 참고하면 SSO 쌉가능하다고 한다.
 */

@Component
public class SimpleAuthFilter {

    @Value(value = "${entrance.key}")
    private String entranceKey;
    @Value(value = "${entrance.value}")
    private String entranceValue;

    public HandlerFilterFunction<ServerResponse, ServerResponse> execute(String requesterHeader) {
        return (request, next) -> {
            ServerRequest serverRequest = ServerRequest.from(request).header(requesterHeader).build();
            List<String> token = serverRequest.headers().header(this.entranceKey);

            if (token.isEmpty()) {
                throw new IllegalArgumentException("인증 실패");
            }

            String entranceKey = token.get(0);
            if (!entranceKey.equals(entranceValue)) {
                throw new IllegalArgumentException("인증 실패");
            }
            ServerResponse response = next.handle(serverRequest);
            return response;
        };
    }
}
