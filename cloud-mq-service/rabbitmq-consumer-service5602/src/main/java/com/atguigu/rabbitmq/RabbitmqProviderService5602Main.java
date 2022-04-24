package com.atguigu.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName RabbitmqProviderService5601Main
 * @Description
 * @date 2022年04月24日 21:57
 */


@EnableRabbit
@SpringBootApplication
public class RabbitmqProviderService5602Main {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProviderService5602Main.class, args);
    }
}
