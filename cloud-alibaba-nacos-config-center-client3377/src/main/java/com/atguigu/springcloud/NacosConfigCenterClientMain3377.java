package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName NacosConfigCenterClientMain3377
 * @Description
 * @date 2021年11月09日 9:59
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigCenterClientMain3377 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigCenterClientMain3377.class, args);
    }
}
