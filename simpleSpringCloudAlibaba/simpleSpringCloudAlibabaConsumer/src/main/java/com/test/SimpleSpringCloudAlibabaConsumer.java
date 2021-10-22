package com.test;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableDubbo
@PropertySource(value = "classpath:/consumer-config.properties")
public class SimpleSpringCloudAlibabaConsumer {
    public static void main(String[] args) {
        SpringApplication.run(SimpleSpringCloudAlibabaConsumer.class, args);
    }
}
