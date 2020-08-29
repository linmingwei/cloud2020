package com.atgugu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface ILoadBalancer {
    public ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
