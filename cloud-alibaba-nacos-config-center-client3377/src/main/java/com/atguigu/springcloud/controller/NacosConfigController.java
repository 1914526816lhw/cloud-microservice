package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName NacosConfigController
 * @Description
 * @date 2021年11月09日 10:02
 */
@RestController
@Slf4j
@RefreshScope  //保证动态刷新
public class NacosConfigController {

    @Value(value = "${config.info}")
    private String configInfo;



    @GetMapping(value = "/nacos/config/getInfo")
    public String getConfigInfo() {
        return configInfo;
    }

}
