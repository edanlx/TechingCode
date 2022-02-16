package com.example.demo.lesson.spring.beandefinition.parent;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ParentMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/example/demo/lesson/spring/beandefinition/parent/parent-example.xml");
        // xml属性继承，可以看到child变成多例了
        System.out.println(context.getBean("child"));
        System.out.println(context.getBean("child"));
    }
}
