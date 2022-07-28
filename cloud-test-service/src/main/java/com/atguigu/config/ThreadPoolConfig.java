package com.atguigu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jiajun
 * @className ThreadPoolConfigura
 * @description 创建线程池
 * @date 2022/4/12 9:54
 */
@Configuration
public class ThreadPoolConfig {

    @Value("${app.thread.core-size}")
    private Integer coreSize;

    @Value("${app.thread.max-size}")
    private Integer maxSize;

    @Value("${app.thread.alive-time}")
    private Long aliveTime;

    @Value("${app.thread.queue-size}")
    private Integer queueSize;

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        BlockingQueue<Runnable> list = new ArrayBlockingQueue<Runnable>(queueSize);
        return new ThreadPoolExecutor(coreSize, maxSize, aliveTime, TimeUnit.MINUTES, list);
    }
}
