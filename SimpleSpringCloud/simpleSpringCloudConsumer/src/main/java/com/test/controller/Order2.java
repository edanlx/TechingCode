package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order2")
/**
 * feign 需要增加注解@EnableFeignClients
 */
public class Order2 {

    @Autowired
    private ProductCenterFeignApi productCenterFeignApi;

    @GetMapping(value = "/echo/{name}")
    public String echo(@PathVariable String name) {
        return productCenterFeignApi.selectProductInfoById(name);

    }
    @GetMapping(value = "/hello")
    public String echo() {
        return "hello";
    }
}
