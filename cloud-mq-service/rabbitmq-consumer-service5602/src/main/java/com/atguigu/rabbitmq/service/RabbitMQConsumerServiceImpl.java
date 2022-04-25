package com.atguigu.rabbitmq.service;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName RabbitMQProviderServiceImpl
 * @Description
 * @date 2022年04月24日 22:09
 */
@Service
@Slf4j
public class RabbitMQConsumerServiceImpl implements IRabbitMQConsumerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue(name = "TEST-QUEUE", durable = "true", autoDelete = "false"),
                            exchange = @Exchange(name = "TEST-EXCHANGE", type = ExchangeTypes.DIRECT, durable = "true", autoDelete = "false"
                            ),
                            key = {"TEST-ROUTE-KEY"}
                    )
            }
    )
    @Override
    public void receiveMsg(JSONObject paramJson, Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println(deliveryTag);
        try {
            System.out.println(paramJson.toJSONString());
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                channel.basicNack(deliveryTag, false, false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
