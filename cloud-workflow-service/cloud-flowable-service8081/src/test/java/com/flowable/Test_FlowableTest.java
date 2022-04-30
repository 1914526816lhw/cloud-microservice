package com.flowable;

import org.flowable.engine.ProcessEngine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lihw
 * @className Test_FlowableTest
 * @date 2022-04-28 15:12
 * @description
 */
    /* 启动项目自动创建表
        1、定义流程: xml
        2、部署流程
        3、启动流程
        4、流程操作
     */
@SpringBootTest
public class Test_FlowableTest {


    @Autowired
    private ProcessEngine processEngine;


    @Test
    public void test(){
        System.out.println("processEngine = " + processEngine);
    }
}

