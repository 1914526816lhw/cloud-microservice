package com.atguigu.rabbitmq.service;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    /*消费端监听队列*/
    @RabbitListener(queues = {"TEST-QUEUE"})
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
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
