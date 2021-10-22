package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
@RefreshScope
/**
 * rest 需要注入bean
 */
public class Order {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${config.appName}")
    private String appName;

    @GetMapping(value = "/echo/{name}")
    public String echo(@PathVariable String name) {
        return "Hello ： " + name + appName;
    }

    @GetMapping(value = "/hello")
    public String echo() {
        return restTemplate.getForObject("http://producer/order/hello/", String.class);
    }
}
