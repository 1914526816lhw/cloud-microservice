package com.atguigu.springcloud.service;

import com.atguigu.springcloud.domain.Order;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName OrderService
 * @Description
 * @date 2021年12月06日 17:29
 */

public interface OrderService {

    /**
     * 创建订单
     *
     * 创建订单 -> 调用库存服务扣减库存 -> 调用账户服务扣减账户余额 -> 修改订单状态
     *
     * @return
     * @params
     */
    void createOrder(Order order);
}
