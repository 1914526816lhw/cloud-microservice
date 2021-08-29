package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * ClassName：OrderZKController
 * Description：
 *
 * @author lihw
 * CreateTime: 2021/1/5 23:49
 * @version 1.0.0
 */
@RestController
@Slf4j
public class OrderZKController {

    private static final String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/zk")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        return result;
    }

    @GetMapping(value = "/consumer/payment/discovery")
    public CommonResult discovery() {
        Object discoveryClient = restTemplate.getForObject(INVOKE_URL + "/payment/discovery", Object.class);
        return new CommonResult(200,"查询成功",discoveryClient);
    }

}
