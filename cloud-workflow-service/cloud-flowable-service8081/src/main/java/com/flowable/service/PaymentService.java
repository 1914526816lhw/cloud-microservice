package com.flowable.service;

import com.atguigu.springcloud.entities.Payment;
import com.flowable.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lihw
 * @className PaymentService
 * @date 2022-05-09 17:47
 * @description
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    public Payment queryPayment(Long id) {
        return paymentMapper.queryPaymentById(id);
    }
}
