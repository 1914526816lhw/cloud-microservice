package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName SeataOrderServiceMain2001
 * @Description
 * @date 2021年12月06日 16:22
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class SeataOrderServiceMain2001 {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderServiceMain2001.class, args);
    }
}
