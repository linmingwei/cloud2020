package com.atgugu.springcloud.controller;

import com.atgugu.springcloud.lb.ILoadBalancer;
import com.atgugu.springcloud.lb.MyLoadBalancer;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ILoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);

    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/"+id, CommonResult.class);

    }
    @GetMapping("/consumer/payment/getEntity/{id}")
    public ResponseEntity<CommonResult> getEntity(@PathVariable("id") Long id) {

        return restTemplate.getForEntity(PAYMENT_URL + "/payment/get/"+id, CommonResult.class);

    }
    @GetMapping("/consumer/payment/lb")
    public String getEntitywithMyLoadBalancer() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances==null || instances.size()==0) return null;
        ServiceInstance server = loadBalancer.instances(instances);


        return restTemplate.getForObject(server.getUri() + "/payment/lb", String.class);

    }

}
