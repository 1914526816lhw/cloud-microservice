package com.flowable.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.entities.CommonResult;
import com.flowable.service.IFlowableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lihw
 * @className FloableController
 * @date 2022-04-29 09:22
 * @description
 */
@RestController
@RequestMapping("/flowable")
@Slf4j
public class FlowableController {

    @Autowired
    private IFlowableService iFlowableService;

    @RequestMapping("/ployProcess")
    public CommonResult startProcess(String userId) {
        iFlowableService.ployProcess(userId);
        return new CommonResult(200, "开始流程成功");
    }


    @RequestMapping("/queryTasksByAssignee")
    public CommonResult queryTasksByAssignee(String processInstanceId, String assignee) {
        return iFlowableService.queryTasksByAssignee(processInstanceId, assignee);
    }

    @RequestMapping("/completeTask")
    public CommonResult completeTask(@RequestBody JSONObject paramJson) {
        iFlowableService.completeTask(paramJson);
        return new CommonResult(200, "完成审核任务成功");

    }

    @RequestMapping("/queryProcessTaskHistory")
    public CommonResult queryProcessTaskHistory(String processInstanceId) {
        return iFlowableService.queryProcessTaskHistory(processInstanceId);
    }

    @RequestMapping("/genProcessDiagram")
    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processInstanceId) throws Exception {
        iFlowableService.genProcessDiagram(httpServletResponse, processInstanceId);
    }
}
