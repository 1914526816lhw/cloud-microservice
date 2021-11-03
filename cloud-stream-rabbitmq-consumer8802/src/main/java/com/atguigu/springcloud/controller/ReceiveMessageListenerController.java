package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName ReceiveMessageListenerController
 * @Description
 * @date 2021年10月30日 23:55
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value(value = "${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者 1 号，----->接收到的消息：" + message.getPayload() + "\t port:" + serverPort);
    }
}
