package com.atguigu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MyselfRule.class)
public class EKOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(EKOrderMain80.class, args);
    }
}
