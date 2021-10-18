package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName CloudConfigCenterMain3344
 * @Description
 * @date 2021年10月15日 10:40
 */
@SpringBootApplication
//激活配置中心
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
