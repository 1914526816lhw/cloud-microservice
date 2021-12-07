package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.CommonResult;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private OrderDao orderDao;

    @PostMapping("/order/createOrder")
    public CommonResult createOrder(@RequestBody Order order) {
        //初始化设置订单状态为 0
        order.setState(0);
        orderService.createOrder(order);
        return new CommonResult(200, "订单创建成功");
    }
}
 
