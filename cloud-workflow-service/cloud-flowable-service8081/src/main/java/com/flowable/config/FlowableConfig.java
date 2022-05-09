package com.flowable.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.plugin.MasterSlaveAutoRoutingPlugin;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;


/**
 * @author lihw
 * @className FlowableConfig
 * @date 2022-04-28 15:59
 * @description
 */
@Configuration
public class FlowableConfig   implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {


    @Autowired
    @Qualifier("flowableDataSource")
    private DataSource flowableDataSource;

    @DS("flowable")
    @Override
    public void configure(SpringProcessEngineConfiguration config) {
        config.setActivityFontName("宋体");
        config.setLabelFontName("宋体");
        config.setAnnotationFontName("宋体");
        config.setCreateDiagramOnDeploy(true);
        config.setDataSource(flowableDataSource);
    }

}
