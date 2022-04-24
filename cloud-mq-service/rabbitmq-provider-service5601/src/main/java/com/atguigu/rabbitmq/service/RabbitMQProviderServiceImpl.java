package com.atguigu.rabbitmq.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName RabbitMQProviderServiceImpl
 * @Description
 * @date 2022年04月24日 22:09
 */
@Service
@Slf4j
public class RabbitMQProviderServiceImpl implements IRabbitMQProviderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public String sendMsg(JSONObject paramJson) {
        rabbitTemplate.convertAndSend("test-exchange", "test-key", paramJson);
        return "消息发送成功";
    }
}
