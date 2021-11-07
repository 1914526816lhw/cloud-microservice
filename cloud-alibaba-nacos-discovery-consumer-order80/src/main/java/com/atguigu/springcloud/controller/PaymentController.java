package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

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

    private static final String INVOKE_URI = "http://nacos-discovery-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/nacos/discovery/{string}")
    public String echo(@PathVariable String string) {

        return restTemplate.getForObject(INVOKE_URI + "/provider/nacos/discovery/" + string, String.class);
    }
}
