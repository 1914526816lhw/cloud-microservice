package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ClassName：PaymentMain8001
 * Description：
 *
 * @author lihw
 * CreateTime: 2020/12/14 22:43
 * @version 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
public class EKPaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(EKPaymentMain8002.class, args);
    }
}
