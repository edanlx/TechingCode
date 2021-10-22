package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
/**
 * rest 需要注入bean
 */
public class Order {

    @Autowired
    // 注册时@LoadBalanced 注解不能少
    private RestTemplate restTemplate;

    @GetMapping(value = "/echo/{name}")
    public String echo(@PathVariable String name) {
        return "Hello ： " + name;
    }

    @GetMapping(value = "/hello")
    public String echo() {
        return restTemplate.getForObject("http://eureka-producer/order/hello/", String.class);
    }
}
