package com.atguigu.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName PaymentService
 * @Description
 * @date 2021年09月05日 21:30
 */
public interface PaymentService {

    /*正常方法*/
    public String paymentInfo_OK(Integer id);

    /*异常方法*/
    public String paymentInfo_Error(Integer id);

    /*======================服务熔断=====================*/
    public String paymentCircuitBreaker(Integer id);
}
