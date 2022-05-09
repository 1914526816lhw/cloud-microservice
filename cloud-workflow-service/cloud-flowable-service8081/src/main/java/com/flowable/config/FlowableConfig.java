package com.flowable.config;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * @author lihw
 * @className FlowableConfig
 * @date 2022-04-28 15:59
 * @description
 */
@Configuration
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    @Autowired
    @Qualifier("flowableDataSource")
    private DataSource flowableDataSource;

    @Override
    @DS("flowable")
    public void configure(SpringProcessEngineConfiguration config) {
        config.setActivityFontName("宋体");
        config.setLabelFontName("宋体");
        config.setAnnotationFontName("宋体");
        config.setCreateDiagramOnDeploy(true);
        config.setDataSource(flowableDataSource);
    }
}
