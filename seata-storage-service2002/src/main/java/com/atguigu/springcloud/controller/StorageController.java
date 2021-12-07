package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.domain.CommonResult;
import com.atguigu.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;


    //扣减库存
    @RequestMapping("/storage/decreaseStorage")
    public CommonResult decreaseStorage(Long productId, Integer count) {
        storageService.decreaseStorage(productId, count);
        return new CommonResult(200, "扣减库存成功！");
    }
}
 
