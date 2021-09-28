package com.atguigu.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName HystrixMainPayment8001
 * @Description
 * @date 2021年09月05日 21:27
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class HystrixMainPayment8001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixMainPayment8001.class, args);
    }

    /*
        此配置是为了服务监控而配置，与服务容错本身无关，springcloud 升级后的坑，
        ServletRegistrationBean 因为 springboot 的默认路径不是 "/hystrix.stream"
        只要在自己的项目里配置上下面的 servlet 就可以了
    */
    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>();
        registrationBean.setServlet(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
