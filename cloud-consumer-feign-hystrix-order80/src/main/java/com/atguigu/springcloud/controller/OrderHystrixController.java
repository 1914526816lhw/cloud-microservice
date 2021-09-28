package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName OrderController
 * @Description
 * @date 2021年09月06日 0:43
 */
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") //全局的
public class OrderHystrixController {


    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/hystrix/ok/{id}")
    public String getOK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.getOK(id);
        log.info("***result:{}" + result);
        return result;
    }


    @GetMapping(value = "/consumer/hystrix/error/{id}")
//    @HystrixCommand(
//            fallbackMethod = "getErrorFallbackMethod",
//            commandProperties = {
//                    //表示 3秒钟 以内就是正常的业务逻辑
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//            }
//    )
    public String getError(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.getError(id);
        log.info("***result:{}" + result);
        return result;
    }

    public String getErrorFallbackMethod(Integer id) {
        return "我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,(┬＿┬)";
    }

    //下面是全局fallback方法
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试,(┬＿┬)";
    }


    /*======================= 服务熔断 =======================*/
    @GetMapping(value = "/consumer/hystrix/circuitbreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {

        String result = paymentHystrixService.paymentCircuitBreaker(id);
        log.info("***result:{}" + result);
        return result;
    }
}