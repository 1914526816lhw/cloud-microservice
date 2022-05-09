package com.flowable.controller;

import com.atguigu.springcloud.entities.CommonResult;
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
    public CommonResult queryPayment(Long id) {
        return new CommonResult<>(200, "根据Id查询成功", paymentService.queryPayment(id));
    }
}


