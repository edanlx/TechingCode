package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class Order {

    @GetMapping(value = "/echo/{name}")
    public String echo(@PathVariable String name) {
        return "Hello ： " + name;
    }

    @GetMapping(value = "/hello")
    public String echo() {
        return "Hello ： ";
    }
}
