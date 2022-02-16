package com.example.demo.lesson.spring.createbean.factorymethod;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryMethodMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/example/demo/lesson/spring/createbean/factorymethod/method-example.xml");
        System.out.println(context.getBean("user1"));
        System.out.println(context.getBean("userFactory"));
        System.out.println(context.getBean("user2"));
    }
}
