package com.test;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo(scanBasePackages = "com.test.service")
@PropertySource(value = "classpath:/provider-config.properties")
public class SimpleSpringCloudAlibabaProducer {
    public static void main(String[] args) {
        SpringApplication.run(SimpleSpringCloudAlibabaProducer.class, args);
    }
}
