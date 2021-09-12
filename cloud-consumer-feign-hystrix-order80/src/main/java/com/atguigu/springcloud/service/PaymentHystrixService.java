package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName OrderService
 * @Description
 * @date 2021年09月06日 0:37
 */

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackServiceImpl.class)
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String getOK(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/error/{id}")
    public String getError(@PathVariable("id") Integer id);

}
