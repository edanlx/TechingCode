package com.example.demo.lesson.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringExample {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(annotationConfigApplicationContext.getBean("user"));
        System.out.println(annotationConfigApplicationContext.getBean("&user"));
    }
}
