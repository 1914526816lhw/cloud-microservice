package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName HystrixMainOrder80
 * @Description
 * @date 2021年09月06日 0:33
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class HystrixMainOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixMainOrder80.class, args);
    }
}
