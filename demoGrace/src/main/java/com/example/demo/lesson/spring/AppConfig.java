package com.example.demo.lesson.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example.demo.lesson.spring")
public class AppConfig {
    @Bean
    public UserBean userBean(UserEntity user) {
        return new UserBean();
    }
}