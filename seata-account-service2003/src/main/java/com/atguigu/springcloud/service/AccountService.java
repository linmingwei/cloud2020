package com.atguigu.springcloud.service;

import com.atguigu.springcloud.domain.CommonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface AccountService {
    void decrease( Long productId, BigDecimal money);
}
