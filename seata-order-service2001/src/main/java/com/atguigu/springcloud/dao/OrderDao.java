package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.domain.Order;


public interface OrderDao {

    //新建订单
    void createOrder(Order order);

    //修改订单状态，从零改为1
    void updateOrderState(Long userId, Integer state);

}
 
