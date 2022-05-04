package com.atguigu.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/* 自动配置
    1、RabbitAutoConfiguration rabbitmq的自动配置类
    2、有自动连接工厂 ConnectionFactory
    3、RabbitProperties 封装了Rabbit 的配置信息
    4、RabbitTemplate 为 rabbitmq 提供了发送和接收信息的模板
    5、创建 queue、exchange、binding 可以在 rabbitmq web界面中事先定义创建，也可以在
        AmqpAdmin: rabbitmq 的系统管理功能组件（通常用于在程序执行过程中创建和删除：队列、交换机、绑定规则）
        提供创建和删除：queue、exchange、binding等功能
    6、@EnableRabbit + @RabbitListener 监听消息队列的内容
 */
//基于注解的rabbitmq模式
@EnableRabbit
@SpringBootApplication
public class RabbitmqProviderService5601Main {


    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProviderService5601Main.class, args);
    }
}
