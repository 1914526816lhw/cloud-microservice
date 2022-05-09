package com.flowable.service;


import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.entities.CommonResult;

import javax.servlet.http.HttpServletResponse;
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

    /*手动部署流程*/
    String ployProcess(String userId);

    /*启动流程*/
    String startProcess(String processInstanceId, Map<String, Object> map);

    /*查询任务*/
    CommonResult queryTasksByAssignee(String processInstanceId, String assignee);

    /*审批完成流程*/
    void completeTask(JSONObject paramJson);

    /*根据流程Id查询流程任务历史数据*/
    CommonResult queryProcessTaskHistory(String processInstanceId);

    void genProcessDiagram(HttpServletResponse httpServletResponse, String processInstanceId) throws Exception;

    /*分页查询所有流程的历史数据*/

}
