package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName SeataStorageServiceMain2002
 * @Description
 * @date 2021年12月06日 22:49
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class SeataStorageServiceMain2002 {
    public static void main(String[] args) {
        SpringApplication.run(SeataStorageServiceMain2002.class, args);
    }
}
