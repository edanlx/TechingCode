package com.example.demo.lesson.spring.simplestart;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextAwareExample implements ApplicationContextAware {

    public ApplicationContextAwareExample() {
        System.out.println("this is ApplicationContextAwareExample");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("this is setApplicationContext");
    }
}
