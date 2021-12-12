package com.atguigu.springcloud.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName QuartzServiceImpl
 * @Description
 * @date 2021年12月12日 10:46
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    @Qualifier("quartzDataSource")
    private DataSource dataSource;

    @Autowired
    private SchedulerFactoryBean schedulerFactory;
    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(JSONObject param) throws Exception {

        String jobClass = param.getString("jobClass");
        JSONObject job = param.getJSONObject("job");
        JSONObject trigger = param.getJSONObject("trigger");

        Class clazz = Class.forName(jobClass);
        /*配置任务的具体实现*/
        //执行任务目标类
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(clazz);
        //是否持久化
        jobDetailFactoryBean.setDurability(true);
        //出现异常是否重新执行
        jobDetailFactoryBean.setRequestsRecovery(true);
        //配置定时任务信息
        jobDetailFactoryBean.setName(job.getString("name"));
        jobDetailFactoryBean.setGroup(job.getString("group"));
        jobDetailFactoryBean.setDescription(job.getString("description"));
        jobDetailFactoryBean.afterPropertiesSet();
        JobDetail jobDetail = jobDetailFactoryBean.getObject();

        /*配置定时任务规则信息*/
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setName(trigger.getString("name"));
        cronTriggerFactoryBean.setGroup(trigger.getString("group"));
        //配置执行的任务 jobDetailFactoryBean

        if (jobDetail == null) {
            throw new Exception("任务为信息为空");
        }
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        //配置执行规则 每 5 秒执行一次
        cronTriggerFactoryBean.setCronExpression(trigger.getString("cronExpression"));
        cronTriggerFactoryBean.afterPropertiesSet();

        /*配置任务执行规则*/
        schedulerFactory.setTriggers(cronTriggerFactoryBean.getObject());
        schedulerFactory.afterPropertiesSet();

        /*开启当前的任务调度器*/
        if (scheduler.checkExists(jobDetail.getKey())) {
            throw new Exception("任务已存在");
        }
        scheduler.start();
    }

    @Override
    public void pauseJob(String jobName, String jobGroup) throws SchedulerException {
        scheduler.pauseJob(new JobKey(jobName, jobGroup));
    }


    @Override
    public void resumeJob(String jobName, String jobGroup) throws SchedulerException {
        scheduler.resumeJob(new JobKey(jobName, jobGroup));
    }


    @Override
    public void updateTriggerCron(String triggerName, String triggerGroup, String jobTime) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    @Override
    public boolean deleteJob(String jobName, String jobGroup) throws SchedulerException {
        scheduler.deleteJob(new JobKey(jobName, jobGroup));
        return true;
    }


    @Override
    public void runAJobNow(String jobName, String jobGroup) throws SchedulerException {
        scheduler.triggerJob(new JobKey(jobName, jobGroup));
    }

    @Override
    public List<JobExecutionContext> getCurrentlyExecutingJobs() throws SchedulerException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //通过连接数据源查询数据库获取，解决分布式下避免获取到的数据不一致问题
        return null;
    }

    @Override
    public JSONArray getAllJob() throws SchedulerException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONArray result = new JSONArray();
        GroupMatcher<JobKey> anyJobGroup = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(anyJobGroup);
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            for (Trigger trigger : triggersOfJob) {
                String jobGroup = jobKey.getGroup();
                String jobName = jobKey.getName();
                String jobDescription = jobDetail.getDescription();
                String className = jobKey.getClass().getName();

                String triggerName = trigger.getKey().getName();
                String triggerGroup = trigger.getKey().getGroup();
                String triggerDescription = trigger.getDescription();
                JobDataMap jobDataMap = trigger.getJobDataMap();
                String nextFireTime = sdf.format(trigger.getNextFireTime());
                String previousFireTime = sdf.format(trigger.getPreviousFireTime());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(TriggerKey.triggerKey(triggerName, triggerGroup));
                String cronExpression = "";
                if (trigger instanceof CronTrigger) {
                    cronExpression = ((CronTrigger) trigger).getCronExpression();
                }
                JSONObject json = new JSONObject();
                json.put("jobGroup", jobGroup);
                json.put("jobName", jobName);
                json.put("jobDescription", jobDescription);
                json.put("className", className);
                json.put("triggerName", triggerName);
                json.put("triggerGroup", triggerGroup);
                json.put("triggerDescription", triggerDescription);
                json.put("jobDataMap", jobDataMap);
                json.put("nextFireTime", nextFireTime);
                json.put("previousFireTime", previousFireTime);
                json.put("triggerState", triggerState);
                json.put("cronExpression", cronExpression);
                result.add(json);
            }
        }
        return result;
    }
}
