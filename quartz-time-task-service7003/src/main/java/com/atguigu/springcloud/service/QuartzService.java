package com.atguigu.springcloud.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName QuartzService
 * @Description
 * @date 2021年12月12日 10:43
 */

public interface QuartzService {

    //添加并启动任务
    void addJob(JSONObject param) throws Exception;

    //停止任务
    void pauseJob(String jobName, String jobGroup) throws SchedulerException;

    //恢复任务
    void resumeJob(String jobName, String jobGroup) throws SchedulerException;

    //更新任务时间表达式 cron
    void updateTriggerCron(String triggerName, String triggerGroup, String jobTime) throws SchedulerException;

    //删除任务
    boolean deleteJob(String jobName, String jobGroup) throws SchedulerException;

    //立即执行一个任务
    void runAJobNow(String jobName, String jobGroup) throws SchedulerException;

    //查询正在执行的任务
    List<JobExecutionContext> getCurrentlyExecutingJobs() throws SchedulerException;

    //查询所有的任务
    JSONArray getAllJob() throws SchedulerException;

}
