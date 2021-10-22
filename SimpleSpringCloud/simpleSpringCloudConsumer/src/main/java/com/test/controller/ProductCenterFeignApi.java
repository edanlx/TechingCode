package com.test.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "eureka-producer")
// fallback可以合hytrix结合
public interface ProductCenterFeignApi {
    @RequestMapping("order/echo/{name}")
    String selectProductInfoById(@PathVariable("name") String name);
}
