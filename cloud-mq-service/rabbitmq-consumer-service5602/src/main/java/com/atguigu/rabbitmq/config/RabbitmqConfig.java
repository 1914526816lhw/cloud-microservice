package com.atguigu.rabbitmq.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName RabbitmqConfig
 * @Description
 * @date 2022年04月25日 14:37
 */
@Configuration
public class RabbitmqConfig {

    /*该配置将消息从序列化转为json格式发送，且生产者与消费者配置保持一致*/
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
