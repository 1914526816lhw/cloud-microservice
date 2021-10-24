package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName ConfigCenterClientMain3355
 * @Description
 * @date 2021年10月18日 12:22
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigCenterClientMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterClientMain3355.class, args);
    }
}
