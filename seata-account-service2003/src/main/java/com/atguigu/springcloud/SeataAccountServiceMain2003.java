package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName SeataAccountServiceMain2003
 * @Description
 * @date 2021年12月07日 10:51
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SeataAccountServiceMain2003 {
    public static void main(String[] args) {
        SpringApplication.run(SeataAccountServiceMain2003.class, args);
    }
}
