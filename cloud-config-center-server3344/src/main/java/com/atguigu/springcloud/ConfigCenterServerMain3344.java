package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName CloudConfigCenterMain3344
 * @Description
 * @date 2021年10月15日 10:40
 */
@SpringBootApplication
@EnableEurekaClient
//激活配置中心
@EnableConfigServer
public class ConfigCenterServerMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterServerMain3344.class, args);
    }
}
