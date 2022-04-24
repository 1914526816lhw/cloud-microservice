package com.atguigu.rabbitmq.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.rabbitmq.service.IRabbitMQProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName RabbitMQSendMsgController
 * @Description
 * @date 2022年04月24日 22:27
 */

@RestController
public class RabbitMQSendMsgController {

    @Autowired
    private IRabbitMQProviderService iRabbitMQProviderService;

    @PostMapping("/sendMsg")
    public String sendMsg(@RequestBody JSONObject paramJson) throws IOException {
        return iRabbitMQProviderService.sendMsg(paramJson);
    }
}
