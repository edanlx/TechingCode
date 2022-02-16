package com.example.demo.lesson.spring.proxy.annon.aspect;

import com.example.demo.lesson.spring.proxy.annon.User;
import com.example.demo.lesson.spring.proxy.annon.aspect.Declare.DeclareExample;
import com.example.demo.lesson.spring.proxy.annon.aspect.Declare.UserService;
import com.example.demo.lesson.spring.proxy.annon.aspect.annon.AspectExample;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AspectMainExample {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean(User.class);
        context.registerBean(AspectExample.class);
        context.registerBean(DeclareExample.class);
        context.refresh();

        // @Before示例
        context.getBean("user", User.class).test();
        context.getBean("user", User.class).test("1","2");

        // @Declare示例
        context.getBean("user", UserService.class).myMethod();
    }
}
