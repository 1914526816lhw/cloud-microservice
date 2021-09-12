package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName PaymentFallbackServiceImpl
 * @Description
 * @date 2021年09月13日 1:14
 */

@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {

    @Override
    public String getOK(Integer id) {
        return "-------- PaymentFallbackServiceImpl fallback ----------";
    }

    @Override
    public String getError(Integer id) {
        return "-------- PaymentFallbackServiceImpl fallback, request timeout or RunException----------";
    }
}
