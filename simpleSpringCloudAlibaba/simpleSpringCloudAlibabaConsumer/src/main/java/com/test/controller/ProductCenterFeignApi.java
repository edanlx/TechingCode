package com.test.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "producer")
public interface ProductCenterFeignApi {
    @RequestMapping("order/echo/{name}")
    String selectProductInfoById(@PathVariable("name") String name);
}
