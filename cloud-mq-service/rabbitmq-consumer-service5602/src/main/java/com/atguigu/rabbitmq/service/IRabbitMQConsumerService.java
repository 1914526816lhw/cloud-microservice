package com.atguigu.rabbitmq.service;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName RabbitMQProviderService
 * @Description
 * @date 2022年04月24日 22:06
 */

public interface IRabbitMQConsumerService {

    /*消费消息*/
    void receiveMsg(JSONObject paramJson, Message message, Channel channel);
}
