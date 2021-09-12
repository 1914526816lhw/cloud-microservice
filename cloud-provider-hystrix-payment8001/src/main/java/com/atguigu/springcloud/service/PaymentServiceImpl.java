package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;


/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName PaymentServiceImpl
 * @Description
 * @date 2021年09月05日 21:33
 */
@Service
public class PaymentServiceImpl implements PaymentService {


    /**
     * 正常访问
     *
     * @return
     * @params
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK，id：" + id + "\t" + "笑脸，哈哈哈";
    }

    /*
       使用 HystrixCommand 注解中 fallbackMethod 属性指的是当前
       方法在调用失败的时候 执行的 paymentInfo_errorHandler 该方法【兜底方法】

       @HystrixProperty(name = "execution.isolation.thread.timeoutMilliseconds", value = "3000") 表示请求改线程的时间为 3秒 是正常峰值
    */
    @HystrixCommand(
            fallbackMethod = "paymentInfo_errorHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")}
    )
    @Override
    public String paymentInfo_Error(Integer id) {
        int time =5000;
//        int a = 10 / 0;
//        System.out.println(a);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_Timeout，id：" + id + "\t" + "哭脸，呜呜呜，耗时" + time + "秒";
    }

    public String paymentInfo_errorHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "系统繁忙或运行报错 paymentInfo_errorHandler，id：" + id + "\t" + "哭脸，呜呜呜";
    }
}
