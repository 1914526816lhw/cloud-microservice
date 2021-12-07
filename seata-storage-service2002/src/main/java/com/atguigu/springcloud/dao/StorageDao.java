package com.atguigu.springcloud.dao;


public interface StorageDao {

    //扣减库存信息
    void decreaseStorage( Long productId, Integer count);
}
 
