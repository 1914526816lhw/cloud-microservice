package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName PaymentController
 * @Description
 * @date 2021年11月06日 16:52
 */
@RestController
@Slf4j
public class PaymentController {

    @Value(value = "${server.port}")
    private String serverPort;

    @GetMapping(value = "/provider/nacos/discovery/{string}")
    public String echo(@PathVariable String string) {
        return "Nacos provider " + serverPort + " : Hello " + string;
    }
}
