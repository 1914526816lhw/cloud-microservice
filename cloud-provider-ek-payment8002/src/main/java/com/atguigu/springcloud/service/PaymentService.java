package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName：PaymentService
 * Description：
 *
 * @author lihw
 * CreateTime: 2020/12/15 0:34
 * @version 1.0.0
 */

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
