package com.atguigu.springcloud.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

@Component
public class MyJobFactory extends SpringBeanJobFactory {

    @Autowired
    //注入autowire的对象工厂
    private AutowireCapableBeanFactory beanFactory;

    //重写Job工厂的createJobInstance方法
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object object = super.createJobInstance(bundle);
        //在autowired中手动加入创建出来的对象
        beanFactory.autowireBean(object);
        return object;
    }
}

