package com.flowable.service;


import java.util.Map;

/**
 * @author lihw
 * @className FlowableService
 * @date 2022-04-28 17:24
 * @description
 */

    /* 启动项目自动创建表
        1、定义流程: xml
        2、部署流程
        3、启动流程
        4、流程操作
     */
public interface IFlowableService {

    /*开始流程*/
    public Object startProcess(Map<String,Object> map);

    /**/
}
