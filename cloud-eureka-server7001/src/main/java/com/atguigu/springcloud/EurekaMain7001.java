package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7001 {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        map.forEach((k, v) -> {
            if(v==1){
    ans.add(k);
            }
        });
        Collections.sort(ans);
        SpringApplication.run(EurekaMain7001.class, args);
    }
}
