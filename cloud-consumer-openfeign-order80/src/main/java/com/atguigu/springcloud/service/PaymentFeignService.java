package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName：PaymentFeign
 * Description：
 *
 * @author lihw
 * CreateTime: 2021/1/30 22:33
 * @version 1.0.0
 */

/*
    接口：面向接口编程。把需要调用的远程接口封装到接口中（映射地址为远程接口的地址）

 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

/*  OpenFeign下接口即完成录入如下操作：

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        URI uri = loadBalancer.instances().getUri();
        return restTemplate.getForObject(uri + "/payment/get/" + id, CommonResult.class);
    }
*/

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/openfeign/timeout")
    CommonResult<Payment> timeout();

}
