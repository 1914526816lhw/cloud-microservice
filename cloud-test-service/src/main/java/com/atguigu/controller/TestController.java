package com.atguigu.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

/**
 * @author lihw
 * @className TestController
 * @date 2022-07-06 17:43
 * @description
 */
@RestController

public class TestController {


    @DeleteMapping("/delete")
    public String test01(Long[] ids){
        for (int i = 0; i < ids.length; i++) {
            System.out.println("ids[i] = " + ids[i]);
        }
        System.out.println("ids.length = " + ids.length);
        return "Hello world";
    }
}
