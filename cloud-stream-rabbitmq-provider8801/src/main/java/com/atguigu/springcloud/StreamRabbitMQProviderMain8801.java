package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName StreamRabbitMQMain8801
 * @Description
 * @date 2021年10月30日 21:21
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitMQProviderMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitMQProviderMain8801.class, args);
    }
}
