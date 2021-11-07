package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName AlibabaNacosDiscoveryProviderPaymentMain9001
 * @Description
 * @date 2021年11月06日 16:50
 */
@SpringBootApplication
//此处开启的注解是：@EnableDiscoveryClient
@EnableDiscoveryClient
public class NacosDiscoveryProviderPaymentMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryProviderPaymentMain9001.class, args);
    }
}
