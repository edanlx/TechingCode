package com.test.controller;

import com.test.service.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order3")
/**
 * dubbo
 */
public class Order3 {

    @DubboReference
    private OrderService orderService;

    @GetMapping(value = "/hello")
    public String echo() {
        return orderService.getHello();
    }
}
