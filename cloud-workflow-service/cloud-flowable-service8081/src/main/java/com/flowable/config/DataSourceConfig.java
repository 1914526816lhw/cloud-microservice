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
//@MapperScan(basePackages = {"com.flowable.mapper"},
//        sqlSessionTemplateRef = "masterSqlSessionTemplate")
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
//
//    @Bean(name = "masterSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(masterDataSource);
//        // 注释的这三行是配置读取映射文件的场景的，我没有使用映射文件，而是注解，所以注释了
////        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
////        Resource[] resources = resourcePatternResolver.getResources("classpath:mapper/*.xml");
////        factoryBean.setMapperLocations(resources);
//        return factoryBean.getObject();
//    }
//
//    @Bean(name = "masterTransactionManager")
//    @Primary
//    public DataSourceTransactionManager test1TransactionManager(@Qualifier("masterDataSource") DataSource masterDataSource) {
//        return new DataSourceTransactionManager(masterDataSource);
//    }
//
//    // @Qualifier 说明一下这个注解，用在有多个一样的对象的情况下，
//    // spring不知道将哪一个对象注入给定的引用时，用这个注解指定注入哪一个对象。
//    // 比如此时，因为多数据源配置，会有2个sqlSessionFactory
//    // 名字定为test1SqlSessionFactory和test2SqlSessionFactory，
//    // 这里是数据源1的配置，所以我指定要注入test1SqlSessionFactory
//    @Bean(name = "masterSqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate test1SqlSessionTemplate(
//            @Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
//    }

}
