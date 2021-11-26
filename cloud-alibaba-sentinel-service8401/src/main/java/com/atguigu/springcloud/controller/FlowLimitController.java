package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName FlowLimitController
 * @Description
 * @date 2021年11月16日 16:17
 */
@RestController
@Slf4j
public class FlowLimitController {

    @Autowired
    private MessageService messageService;

    /*流控模式：直接流控、关联流控*/
    @GetMapping(value = "/testA")
    public String testA() {
       /* try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
//        return "--------testA";
        return messageService.getMessage("AAAAAA");
    }

    @GetMapping(value = "/testB")
    public String testB() {
//        return "--------testB";
        return messageService.getMessage("BBBBBB");
    }

    /*熔断降级策略*/
    //慢调用比例
    @GetMapping(value = "/slowCall")
    @SentinelResource(value = "slowCall", fallback = "slowCall_fallback")
    public String slowCall() {
        String result = "";
        try {
            Thread.sleep(1000);
            result = "slowCall";
            System.out.println("slowCall");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String slowCall_fallback() {
        System.out.println("慢调用测试");
        return "慢调用测试";
    }


    //异常比例、异常数
    @GetMapping(value = "/exceptionRate")
    @SentinelResource(value = "exceptionRate", fallback = "exceptionRate_fallback")
    public String exceptionRate() {
        int result = 10 / 0;
        return "sucess";
    }

    public String exceptionRate_fallback(Throwable throwable) {
        System.out.println("异常比例测试");
        return "异常比例测试";
    }

    /*热点key规则限流*/
    //对 p1 参数进行热点限流
    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "------testHotKey";
    }
    public String deal_testHotKey(@RequestParam(value = "p1", required = false) String p1,
                                  @RequestParam(value = "p2", required = false) String p2,BlockException e) {
        return "------deal_testHotKey";
    }
}
