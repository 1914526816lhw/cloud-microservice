package com.flowable.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {


    @Bean(name = "flowableDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.flowable")
    public DataSource flowableDataSource() {
        return DataSourceBuilder.create()
                .type(DruidDataSource.class)
                .build();
    }

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
                .type(DruidDataSource.class)
                .build();
    }
}
