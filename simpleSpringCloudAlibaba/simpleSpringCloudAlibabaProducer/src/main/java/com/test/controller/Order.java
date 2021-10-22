package com.test.controller;

import com.test.service.TidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class Order {

    @Autowired
    private TidService tidService;

    @GetMapping(value = "/echo/{name}")
    public String echo(@PathVariable String name) {
        return "Hello ： " + name;
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello ： ";
    }

    @GetMapping(value = "/seataSaga")
    public String seataSaga() {
        tidService.executeTestSql();
        return "Hello ： ";
    }
}
