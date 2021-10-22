package com.test.controller;

import com.test.service.TidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order5")
@RefreshScope
/**
 * rest 需要注入bean
 */
public class Order5 {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TidService tidService;

    @GetMapping(value = "/hello")
    public String hello() {
        tidService.executeTestSql();
        return restTemplate.getForObject("http://producer/order/seataSaga/", String.class);
    }
}
