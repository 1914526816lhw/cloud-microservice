package com.atguigu.springcloud.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.service.QuartzService;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName CreateJobAndTrigger
 * @Description
 * @date 2021年12月11日 15:24
 */
@RestController
@RequestMapping("/job")
public class QuartzTaskController {

    @Autowired
    private QuartzService quartzService;


    //添加任务并启动
    @PostMapping(value = "/createJob")
    public CommonResult createTask(@RequestBody JSONObject param) {


        try {
            quartzService.addJob(param);

        } catch (Exception e) {
            return new CommonResult<>(400, "添加并开启任务失败",e);
        }
        return new CommonResult(200, "添加并开启任务成功");
    }

    //暂停任务
    @PostMapping(value = "/pauseJob")
    public CommonResult pauseJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
        try {
            quartzService.pauseJob(jobName, jobGroup);
        } catch (SchedulerException e) {
            return new CommonResult<>(500, "暂停任务失败", e);
        }
        return new CommonResult(200, "暂停任务成功");
    }

    //恢复任务
    @PostMapping(value = "/resumeJob")
    public CommonResult resumeJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
        try {
            quartzService.resumeJob(jobName, jobGroup);
        } catch (SchedulerException e) {
            return new CommonResult<>(500, "恢复任务失败", e);
        }
        return new CommonResult(200, "恢复任务成功");
    }

    //更新任务时间表达式 cron
    @PostMapping(value = "/updateJob")
    public CommonResult updateJob(@RequestParam("triggerName") String triggerName, @RequestParam("triggerGroup") String triggerGroup, @RequestParam("jobTime") String jobTime) {
        try {
            quartzService.updateTriggerCron(triggerName, triggerGroup, jobTime);
        } catch (SchedulerException e) {
            return new CommonResult<>(500, "修改失败", e);
        }
        return new CommonResult(200, "修改成功");
    }

    //删除任务
    @DeleteMapping(value = "/deleteJob")
    public CommonResult deleteJob(String jobName, String jobGroup) {
        try {
            if (!quartzService.deleteJob(jobName, jobGroup)) {
                return new CommonResult<>(500, "删除任务失败", "任务正在执行中");
            }
        } catch (SchedulerException e) {
            return new CommonResult<>(500, "删除任务失败", e);
        }
        return new CommonResult(200, "删除任务成功");
    }

    //立即执行一个任务
    @PostMapping(value = "/runAJobNow")
    public CommonResult runAJobNow(String jobName, String jobGroup) {
        try {
            quartzService.runAJobNow(jobName, jobGroup);
        } catch (SchedulerException e) {
            return new CommonResult<>(500, "执行任务失败", e);
        }
        return new CommonResult(200, "执行任务成功");
    }

    @GetMapping("/getCurrentlyExecutingJobs")
    public CommonResult getCurrentlyExecutingJobs() {
        List<JobExecutionContext> executionContexts = null;
        try {
            executionContexts = quartzService.getCurrentlyExecutingJobs();
        } catch (SchedulerException e) {
            return new CommonResult<>(500, "获取正在执行的任务信息失败");
        }
        return new CommonResult<>(200, "获取正在执行的任务信息成功", executionContexts);
    }

    @GetMapping("/getAllJob")
    public CommonResult getAllJob() {
        JSONArray result = null;
        try {
            result = quartzService.getAllJob();
        } catch (SchedulerException e) {
            return new CommonResult<>(500, "获取失败");
        }
        return new CommonResult<>(200, "获取成功", result);
    }
}
