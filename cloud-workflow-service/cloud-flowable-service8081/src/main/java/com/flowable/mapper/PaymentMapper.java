package com.flowable.mapper;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName PaymentMapper
 * @Description
 * @date 2022年05月09日 23:48
 */

@Repository
public interface PaymentMapper {

    Payment queryPaymentById(Long id);
}
