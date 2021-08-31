package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName：OrderMain80
 * Description：
 *
 * @author lihw
 * CreateTime: 2021/1/5 23:09
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZKOrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(ZKOrderMain80.class, args);
    }
}
