package com.flowable.controller;

import com.alibaba.fastjson.JSONObject;
import com.flowable.common.CommonResult;
import com.flowable.service.IFlowableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lihw
 * @className FloableController
 * @date 2022-04-29 09:22
 * @description
 */
@RestController
@RequestMapping("/flowable")
@Slf4j
public class FloableController {

    @Autowired
    private IFlowableService iFlowableService;

    @RequestMapping("/startProcess")
    public CommonResult startProcess(@RequestBody Map<String, Object> map) {
        log.info(map.toString());
        iFlowableService.startProcess(map);
        return new CommonResult(200,"开始流程成功");
    }


    @RequestMapping("/queryTasksByAssignee")
    public CommonResult queryTasksByAssignee(String assignee) {
        return iFlowableService.queryTasksByAssignee(assignee);
    }

    @RequestMapping("/completeTask")
    public CommonResult completeTask(@RequestBody JSONObject paramJson) {
        iFlowableService.completeTask(paramJson);
        return new CommonResult(200,"完成审核任务成功");

    }

    @RequestMapping("/queryProcessTaskHistory")
    public CommonResult queryProcessTaskHistory(String processId){
        return iFlowableService.queryProcessTaskHistory(processId);
    }
}
