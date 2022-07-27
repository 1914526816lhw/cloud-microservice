package com.atguigu.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author lihw
 * @className ConsumeFilter
 * @date 2022-06-01 11:20
 * @description
 */
@Component
public class ConsumeFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //过滤校验逻辑

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
