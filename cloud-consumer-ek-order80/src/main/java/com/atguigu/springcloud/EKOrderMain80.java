package com.atguigu.springcloud;


import com.atguigu.springcloud.annotation.RibbonLoadPolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@RibbonLoadPolicy //自定义的 Ribbon 负载策略注解
public class EKOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(EKOrderMain80.class, args);
    }
}
