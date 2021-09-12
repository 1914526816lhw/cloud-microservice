package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName PaymentController
 * @Description
 * @date 2021年09月05日 21:49
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String getOK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("***result:{}" + result);
        return result;
    }

    @GetMapping(value = "/payment/hystrix/error/{id}")
    public String getError(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_Error(id);
        log.info("***result:{}" + result);
        return result;
    }
}
