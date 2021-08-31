package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName：Payment8006
 * Description：
 *
 * @author lihw
 * CreateTime: 2021/1/19 16:27
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulPaymentMain8006 {

    public static void main(String[] args) {
        SpringApplication.run(ConsulPaymentMain8006.class, args);
    }
}
