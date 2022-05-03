package com.flowable.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flowable.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lihw
 * @className FlowableServiceImpl
 * @date 2022-04-29 09:19
 * @description
 */
@Service
@Slf4j
public class FlowableServiceImpl implements IFlowableService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;


    @Transactional
    @Override
    public void startProcess(Map<String, Object> map) {
        ProcessInstance myHolidayUI = runtimeService.startProcessInstanceByKey("MyHolidayUI", map);
        log.info("myHolidayUI.getId() = " + myHolidayUI.getId());
        log.info("myHolidayUI.getProcessDefinitionId() = " + myHolidayUI.getProcessDefinitionId());
        log.info("myHolidayUI.getActivityId() = " + myHolidayUI.getActivityId());
    }

    @Transactional
    @Override
    public CommonResult queryTasksByAssignee(String assignee) {
        JSONArray array = new JSONArray();
        List<Task> list = taskService.createTaskQuery().taskAssignee(assignee).list();

        list.forEach(el -> {
            JSONObject result = new JSONObject();
            result.put("taskId", el.getId());
            result.put("taskName", el.getName());
            array.add(result);
        });
        return new CommonResult<>(200, "查询任务成功", array);
    }

    @Override
    public void completeTask(JSONObject paramJson) {
        //代理人对象
        String assignee = paramJson.getString("assignee");
        Task task = taskService.createTaskQuery().taskAssignee(assignee).singleResult();
        //创建流程变量
        Map<String, Object> map = new HashMap<>();
        map.put("approved", paramJson.getBoolean("approved"));
        //完成任务
        taskService.complete(task.getId(), map);
    }

    @Override
    public CommonResult queryProcessTaskHistory(String processId) {
        JSONArray array = new JSONArray();

        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processDefinitionId(processId)
                .finished()
                .orderByHistoricActivityInstanceEndTime()
                .asc()
                .list();
        list.forEach(history -> {
            JSONObject result = new JSONObject();
            result.put("activityId", history.getActivityId());
            result.put("activityName", history.getActivityName());
            result.put("assignee",history.getAssignee());
            result.put("DurationInMillis",history.getDurationInMillis());
            array.add(result);
        });
        return new CommonResult<>(200, "根据流程ID查询流程历史任务成功", array);

    }
}
