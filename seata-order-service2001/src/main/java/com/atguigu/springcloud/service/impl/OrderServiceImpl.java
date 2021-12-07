package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.serviceRPC.AccountService;
import com.atguigu.springcloud.service.serviceRPC.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName OrderServiceImpl
 * @Description
 * @date 2021年12月06日 17:32
 */
@Service
@Slf4j
public class OrderServiceImpl  implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 创建订单 -> 调用库存服务扣减库存 -> 调用账户服务扣减账户余额 -> 修改订单状态
     *
     * @return
     * @params
     */
    @GlobalTransactional(name = "gtx_create_order", rollbackFor = Exception.class)
    @Override
    public void createOrder(Order order) {

        //创建订单
        log.info(">>>>>开始创建订单");
        orderDao.createOrder(order);
        //扣减库存
        log.info(">>>>>扣减库存");
        storageService.decreaseStorage(order.getProductId(), order.getCount());
        //扣减余额
        log.info(">>>>>扣减余额");
        accountService.decreaseAccount(order.getUserId(), order.getMoney());
        //修改（订单）状态
        log.info(">>>>>修改（订单）状态");
        orderDao.updateOrderState(order.getUserId(), order.getState());
        log.info(">>>>>订单完成");
    }
}
