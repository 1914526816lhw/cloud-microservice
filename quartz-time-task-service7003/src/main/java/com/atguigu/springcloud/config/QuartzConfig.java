package com.atguigu.springcloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName QuartzConfig
 * @Description
 * @date 2021年12月11日 11:38
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private MyJobFactory myJobFactory;

    //配置数据源
    //这里可以不使用@Bean 交给spring管理，否则可能出现默认数据源的问题
    @Bean("quartzDataSource")
    public DataSource druidDataSource() throws IOException {
        //获取quartz数据源配置文件
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartzdb.properties"));
        //获取配置 properties 配置文件对象
        //在quartzdb.properties中的属性被读取并注入后在初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        Properties property = propertiesFactoryBean.getObject();
        //创建数据源配置对象
        DruidDataSource dataSource = new DruidDataSource();
        assert property != null;
        dataSource.setDriverClassName(property.getProperty("db.driver-class-name"));
        dataSource.setUrl(property.getProperty("db.url"));
        dataSource.setUsername(property.getProperty("db.username"));
        dataSource.setPassword(property.getProperty("db.password"));
        return dataSource;
    }

    //配置 Quartz 配置文件
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后在初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    //线程池配置
    @Bean
    public Executor schedulerThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(50);
        return executor;
    }


    //配置任务调度工厂,用来生成任务调度器,这是quartz的核心
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //开启更新job
        factory.setOverwriteExistingJobs(true);
        //如果不配置就会使用quartz.properties中的instanceName
        //factory.setSchedulerName("Cluster_Scheduler");
        //配置数据源,这是quartz使用的表的数据库存放位置
        factory.setDataSource(druidDataSource());
        //设置实例在spring容器中的key
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        //配置线程池
        factory.setTaskExecutor(schedulerThreadPool());
        //配置Quartz配置文件
        factory.setQuartzProperties(quartzProperties());
        //将自定义的MyJobFactory注入配置类,并添加如下配置,
        //配置使用spring的autowired的对象,在job中进行对象的注入
        factory.setJobFactory(myJobFactory);
        //设置延时启动,保证job中的属性的注入
        factory.setStartupDelay(5);
        //配置任务执行规则,参数是一个可变数组
//        factory.setTriggers();
        return factory;
    }

    @Bean
    public Scheduler scheduler() throws IOException, SchedulerException {
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        //设置为启动的时候恢复所有任务
        scheduler.resumeAll();
        return scheduler;
    }
}
