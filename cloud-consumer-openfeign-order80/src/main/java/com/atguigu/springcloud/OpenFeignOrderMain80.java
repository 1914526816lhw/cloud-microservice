package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName：EnableFeignClients
 * Description：
 *
 * @author lihw
 * CreateTime: 2021/1/30 22:14
 * @version 1.0.0
 */
@SpringBootApplication
@EnableFeignClients
public class OpenFeignOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignOrderMain80.class, args);
    }

}
