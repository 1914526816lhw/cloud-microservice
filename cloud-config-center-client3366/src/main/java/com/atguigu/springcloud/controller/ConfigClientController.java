package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName ConfigClientController
 * @Description
 * @date 2021年10月18日 12:28
 */
@RefreshScope
@RestController
public class ConfigClientController {

    @Value(value = "${server.port}")
    private String serverPort;

    @Value(value = "${config.info}")
    private String configInfo;

    @GetMapping(value = "/getConfigInfo")
    public String getConfigInfo() {
        return "serverPort：" + serverPort + "\n configInfo：" + configInfo;
    }
}
