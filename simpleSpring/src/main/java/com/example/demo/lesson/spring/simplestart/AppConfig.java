package com.example.demo.lesson.spring.simplestart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example.demo.lesson.spring.simplestart")
public class AppConfig {
    @Bean
    public UserBean userBean() {
        return new UserBean();
    }
}
