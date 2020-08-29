package com.atgugu.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class MyLoadBalancer implements ILoadBalancer {
    private AtomicInteger current = new AtomicInteger(0);

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {

        return serviceInstances.get(getAndIncrement() % serviceInstances.size());
    }

    private int getAndIncrement() {
        int next, cur;
        do{
            cur = current.get();
            next = cur+ 1;
            if(next>=Integer.MAX_VALUE) next = 0;

        }while (!current.compareAndSet(cur,next));
        log.info("***** next:"+next);
        return next;
    }

}
