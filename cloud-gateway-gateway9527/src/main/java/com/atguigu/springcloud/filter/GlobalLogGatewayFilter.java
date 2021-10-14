package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName GlobalLogGatewayFilter
 * @Description
 * @date 2021年10月14日 10:14
 */
@Component
@Slf4j
public class GlobalLogGatewayFilter implements GlobalFilter, Ordered {

    /**
     * 全局日志网关过滤器：
     *  ServerWebExchange：表示Web请求服务，可获取请求HttpServletRequest、响应HttpServletResponse等
     *  chain：表示网关过滤连
     *
     * @return
     * @params
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("***********Come in GlobalLogGatewayFilter：" + new Date());

        ServerHttpRequest request = exchange.getRequest();
        String name = request.getQueryParams().getFirst("name");
        if (name == null) {

            log.info("***********用户名不能为空,非法用户");

            //验证失败，设置请求失败状态
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            //最后终止向下一个过滤器方法提供委托，并返回响应
            return exchange.getResponse().setComplete();
        }
        // 放行，并将委托给过滤链中的下一个过滤器
        return chain.filter(exchange);
    }

    /**
     * 可以在全局网关过滤器配置类中加入 @Order 注解代替实现Ordered接口
     * 加载过滤器的优先级,数值越小，加载优先级越高；反之越小
     *
     * @return
     * @params
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
