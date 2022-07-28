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


    @PostMapping("/test")
    public String test01(@RequestParam("phone") String phone){
        System.out.println(phone);
        return "Hello world";
    }
}
