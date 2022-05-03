package com.flowable.service;


import com.alibaba.fastjson.JSONObject;
import com.flowable.common.CommonResult;

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

    /*启动流程*/
    public void startProcess(Map<String,Object> map);

    /*查询任务*/
    public CommonResult queryTasksByAssignee(String assignee);

    /*审批流程*/
    public void completeTask(JSONObject paramJson);

    /*查询流程任务历史数据*/
    public CommonResult queryProcessTaskHistory(String processId);
}
