package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName ConfigCenterClientMain3366
 * @Description
 * @date 2021年10月24日 15:37
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigCenterClientMain3366 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterClientMain3366.class, args);
    }
}
