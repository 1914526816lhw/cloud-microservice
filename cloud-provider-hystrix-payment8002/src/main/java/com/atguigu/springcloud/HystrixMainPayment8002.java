package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName HystrixMainPayment8001
 * @Description
 * @date 2021年09月05日 21:27
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class HystrixMainPayment8002 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixMainPayment8002.class, args);
    }
}
