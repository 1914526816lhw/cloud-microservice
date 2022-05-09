package com.flowable.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flowable.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
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
    private ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Value("${flowable.process-file.work-order.path}")
    private String processFilePath;
    @Value("${flowable.process-file.work-order.process-name}")
    private String processName;

    @Override
    public String ployProcess(String userId) {
        File file = new File(processFilePath);
        byte[] data = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            data = bos.toByteArray();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Deployment deploy = repositoryService
                .createDeployment()
                .addBytes(file.getName(),data)
                .name(processName)
                .deploy();

        //启动流程
        System.out.println("deploy.toString() = " + deploy.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("user1", userId);
        String deployId = deploy.getId();
        log.info("deployId = " + deployId);

        //查询流程
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
        String processInstanceId = startProcess(processDefinition.getId(), map);
        log.info("processInstanceId = " + processInstanceId);
        return processInstanceId;
    }


    @Transactional
    @Override
    public String startProcess(String processDefinitionId, Map<String, Object> map) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, map);
        log.info("myHolidayUI.getId() = " + processInstance.getId());
        log.info("myHolidayUI.getProcessDefinitionId() = " + processInstance.getProcessDefinitionId());
        log.info("myHolidayUI.getActivityId() = " + processInstance.getActivityId());
        String processInstanceId = processInstance.getId();
        return processInstanceId;
    }

    @Transactional
    @Override
    public CommonResult queryTasksByAssignee(String processInstanceId, String assignee) {
        JSONArray array = new JSONArray();
        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .taskAssignee(assignee)
                .list();

        taskList.forEach(task -> {
            JSONObject result = new JSONObject();
            result.put("taskId", task.getId());
            result.put("taskName", task.getName());
            result.put("assignee", task.getAssignee());
            result.put("processInstanceId", task.getProcessInstanceId());
            array.add(result);
        });
        return new CommonResult<>(200, "查询任务成功", array);
    }

    @Transactional
    @Override
    public void completeTask(JSONObject paramJson) {
        //流程Id
        String processInstanceId = paramJson.getString("processInstanceId");
        //代理人对象
        String assignee = paramJson.getString("assignee");
        boolean approved = paramJson.getBoolean("approved");
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .taskAssignee(assignee)
                .singleResult();
        //创建流程变量
        Map<String, Object> map = new HashMap<>();
        map.put("approved", approved);
        //完成任务
        taskService.complete(task.getId(), map);
    }

    @Override
    public CommonResult queryProcessTaskHistory(String processInstanceId) {
        JSONArray array = new JSONArray();

        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .finished()
                .orderByHistoricActivityInstanceEndTime()
                .asc()
                .list();
        list.forEach(history -> {
            JSONObject result = new JSONObject();
            result.put("activityId", history.getActivityId());
            result.put("activityName", history.getActivityName());
            result.put("assignee", history.getAssignee());
            result.put("DurationInMillis", history.getDurationInMillis());
            array.add(result);
        });
        return new CommonResult<>(200, "根据流程ID查询流程历史任务成功", array);
    }

    @Override
    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processInstanceId) throws Exception {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        //流程走完的不显示图
        if (pi == null) {
            return;
        }
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        String InstanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(InstanceId).list();

        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, processEngineConfiguration.getActivityFontName(), processEngineConfiguration.getLabelFontName(), processEngineConfiguration.getAnnotationFontName(), processEngineConfiguration.getClassLoader(), 1.0, false);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = httpServletResponse.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
