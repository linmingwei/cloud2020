package com.atguigu.springcloud.service;


import com.atguigu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {
    void create(Order order);

    void update( Long userId,  Integer status);

}
