package com.flowable.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author lihw
 * @className FlowableDSConfig
 * @date 2022-05-09 11:19
 * @description
 */

@Configuration
public class FlowableDSConfig {


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

}
