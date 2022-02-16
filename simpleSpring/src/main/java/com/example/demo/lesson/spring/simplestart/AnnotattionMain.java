package com.example.demo.lesson.spring.simplestart;

import com.example.demo.lesson.spring.simplestart.event.OrderEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotattionMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // 发布事件
        context.publishEvent(new OrderEvent(context,"ddddd"));
    }
}
