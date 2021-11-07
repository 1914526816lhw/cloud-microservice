package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName ApplicationContext
 * @Description
 * @date 2021年11月06日 22:38
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //使 RestTemplate 具备负载均衡的能力
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
