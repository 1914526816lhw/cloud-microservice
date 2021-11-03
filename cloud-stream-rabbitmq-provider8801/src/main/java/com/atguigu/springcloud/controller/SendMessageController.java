package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName SendMessageController
 * @Description
 * @date 2021年10月30日 23:08
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProviderService messageProviderService;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProviderService.send();
    }

}
