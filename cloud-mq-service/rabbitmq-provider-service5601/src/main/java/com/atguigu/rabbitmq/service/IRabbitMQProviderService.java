package com.atguigu.rabbitmq.service;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName RabbitMQProviderService
 * @Description
 * @date 2022年04月24日 22:06
 */

public interface IRabbitMQProviderService {

    /*发送消息*/
    String sendMsg(JSONObject paramJson) throws IOException;
}
