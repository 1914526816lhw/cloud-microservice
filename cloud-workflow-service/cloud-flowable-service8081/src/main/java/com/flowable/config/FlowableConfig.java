//package com.flowable.config;
//
//import org.flowable.spring.SpringProcessEngineConfiguration;
//import org.flowable.spring.boot.EngineConfigurationConfigurer;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * @author lihw
// * @className FlowableConfig
// * @date 2022-04-28 15:59
// * @description
// */
//@Configuration
//public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {
//
//
//    @Override
//    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
//        //当监测到数据库表版本不一致是，会自动升级
//        springProcessEngineConfiguration.setDatabaseSchemaUpdate(SpringProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//        //关闭定时任务
//        springProcessEngineConfiguration.setAsyncExecutorActivate(false);
//    }
//}
