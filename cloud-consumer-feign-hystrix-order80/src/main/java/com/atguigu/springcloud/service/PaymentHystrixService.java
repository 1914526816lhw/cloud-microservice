package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
@DefaultProperties(
        commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500"),
                @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间范围
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //失败率达到多少后跳闸
        }
)
public interface PaymentHystrixService {


    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String getOK(@PathVariable("id") Integer id);


    @GetMapping(value = "/payment/hystrix/error/{id}")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")
    public String getError(@PathVariable("id") Integer id);


     /*======================= 服务熔断 =======================*/
    @GetMapping(value = "/payment/hystrix/circuitbreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
