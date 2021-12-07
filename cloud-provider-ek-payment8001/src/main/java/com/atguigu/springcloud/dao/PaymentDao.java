package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName：PaymentCloud
 * Description：
 *
 * @author lihw
 * CreateTime: 2020/12/14 23:54
 * @version 1.0.0
 */
@Mapper
public interface PaymentDao {

    /*新增*/
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
