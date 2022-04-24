package com.atguigu.rabbitmq.service;

import com.alibaba.fastjson.JSON;
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
import java.nio.charset.Charset;

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
                            value = @Queue(name = "test-queue"),
                            exchange = @Exchange(name = "test-exchange", type = ExchangeTypes.DIRECT, durable = "true"),
                            key = {"test-key"}
                    )
            }
    )
    @Override
    public void receiveMsg(JSONObject paramJson,Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println(deliveryTag);
        System.out.println(channel.isOpen());
        try {
            System.out.println(paramJson.toJSONString());
            channel.basicAck(deliveryTag,false);
        }catch (Exception e){
            e.printStackTrace();
            try {
                channel.basicNack(deliveryTag,false,false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
