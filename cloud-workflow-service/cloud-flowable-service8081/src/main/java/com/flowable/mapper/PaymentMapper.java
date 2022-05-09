package com.flowable.mapper;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName PaymentMapper
 * @Description
 * @date 2022年05月09日 23:48
 */

@Mapper
public interface PaymentMapper {

    Payment queryPaymentById(Long id);
}
