package com.flowable.controller;

import com.alibaba.fastjson.JSONObject;
import com.flowable.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihw
 * @className PaymentController
 * @date 2022-05-09 17:49
 * @description
 */

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/queryPayment")
    public JSONObject queryPayment(){
        return paymentService.queryPayment();
    }
}


