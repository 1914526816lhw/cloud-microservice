package com.atguigu.springcloud.service;

public interface StorageService {

    // 扣减库存
    void decreaseStorage(Long productId, Integer count);
}
 
