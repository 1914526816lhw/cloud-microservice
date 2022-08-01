package com.atguigu.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

/**
 * @author lihw
 * @className CloudRedisServiceApplication
 * @date 2022-07-01 16:40
 * @description
 */

@SpringBootApplication
public class RedisServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisServiceApplication.class, args);
    }
}
