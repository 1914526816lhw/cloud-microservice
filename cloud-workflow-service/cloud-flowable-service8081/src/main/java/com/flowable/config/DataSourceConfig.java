package com.flowable.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author lihw
 * @className FlowableDSConfig
 * @date 2022-05-09 11:19
 * @description
 */

@Configuration
public class DataSourceConfig {


    /**
     * 将flowableDB注册到容器中
     *
     * @return
     */
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
