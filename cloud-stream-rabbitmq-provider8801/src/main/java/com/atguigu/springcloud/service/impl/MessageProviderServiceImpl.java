package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName MessageProviderServiceImpl
 * @Description
 * @date 2021年10月30日 21:26
 */

@Slf4j
@EnableBinding(Source.class) //定义消息的推送管道，即：源
/*此处不再需要引入 spring 注解 @Service，这里的业务实现类是与RabbitMQ配合的，使用的 SpringCloud Stream 的注解*/
public class MessageProviderServiceImpl implements IMessageProviderService {

    @Resource
    private MessageChannel output; //消息发送管道


    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("*****serial：" + serial);
        return "RabbitMQ 消息发送方：" + serial;
    }
}
