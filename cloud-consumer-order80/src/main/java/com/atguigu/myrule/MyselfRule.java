package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName：MyselfRule
 * Description：
 *
 * @author lihw
 * CreateTime: 2021/1/20 14:32
 * @version 1.0.0
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule() {
        return new RandomRule(); //定义为随机
    }
}
