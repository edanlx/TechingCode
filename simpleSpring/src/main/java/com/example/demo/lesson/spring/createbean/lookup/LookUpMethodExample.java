package com.example.demo.lesson.spring.createbean.lookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LookUpMethodExample {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean(CreateBeanProToTypeUser.class);
        context.registerBean(CreateBeanProToType2User.class);
        context.registerBean(CreateBeanProxyUser.class);
        context.refresh();
        /**
         * com.example.demo.lesson.spring.createbean.lookup.CreateBeanProToTypeUser@4d14b6c2
         * com.example.demo.lesson.spring.createbean.lookup.CreateBeanProToTypeUser@7e990ed7
         * com.example.demo.lesson.spring.createbean.lookup.CreateBeanProToType2User@c05fddc
         * com.example.demo.lesson.spring.createbean.lookup.CreateBeanProToType2User@c05fddc
         * 使用lookUp注解可以保证每次都获得新对象
         */
        System.out.println(context.getBean("createBeanTargetUser", CreateBeanProxyUser.class).getUser());
        System.out.println(context.getBean("createBeanTargetUser", CreateBeanProxyUser.class).getUser());
        System.out.println(context.getBean("createBeanTargetUser", CreateBeanProxyUser.class).createBeanProToType2User);
        System.out.println(context.getBean("createBeanTargetUser", CreateBeanProxyUser.class).createBeanProToType2User);
    }
}
