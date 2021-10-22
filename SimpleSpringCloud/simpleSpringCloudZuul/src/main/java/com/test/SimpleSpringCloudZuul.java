package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SimpleSpringCloudZuul {
    public static void main(String[] args) {
        SpringApplication.run(SimpleSpringCloudZuul.class, args);
    }
}
