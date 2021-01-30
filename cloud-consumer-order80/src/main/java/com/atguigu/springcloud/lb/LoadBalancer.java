package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * ClassName：LoadBalance
 * Description：
 *
 * @author lihw
 * CreateTime: 2021/1/21 15:43
 * @version 1.0.0
 */
public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);

}
