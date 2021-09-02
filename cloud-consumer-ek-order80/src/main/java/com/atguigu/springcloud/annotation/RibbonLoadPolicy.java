package com.atguigu.springcloud.annotation;

import com.atguigu.myrule.MyselfRule;
import com.atguigu.springcloud.lb.MyLB;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

import java.lang.annotation.*;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName RibbonLoadPolicy
 * @Description
 * @date 2021年09月02日 14:18
 */

/**
 * 此注解是用于 Ribbon 实现客户端负载均衡自定义注解
 *
 * @return
 * @params
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
@RibbonClients(value = {
        @RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = {MyLB.class}),
})
public @interface RibbonLoadPolicy {

}
