package com.atguigu.rabbitmq.config;

import com.atguigu.rabbitmq.util.RabbitmqUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName RabbitmqConfig
 * @Description
 * @date 2022年04月25日 10:28
 */

@Configuration
public class RabbitmqConfig {

    /*该配置将消息从序列化转为json格式发送，且生产者与消费者配置保持一致*/
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /*设置队列*/
    @Bean
    public Queue queue() {
        return new Queue(RabbitmqUtils.RabbitmqQueues.TEST_QUEUE, true, false, false);
    }

    /*设置交换机*/
    @Bean
    public Exchange directExchange() {
        return new DirectExchange(RabbitmqUtils.RabbitmqExchanges.TEST_EXCHANGE, true, false, null);
    }
        /*将队列和交换机通过routingKey绑定*/
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(RabbitmqUtils.RabbitmqRoutingKeys.TEST_ROUTE_KEY).noargs();
    }


}
