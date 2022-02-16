package com.example.demo.lesson.spring.proxy.advice;

import org.springframework.aop.framework.ProxyFactory;

public class AdviceMainExample {
    public static void main(String[] args) {
        AdviceUser target = new AdviceUser();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        // 按照链路执行
//        proxyFactory.addAdvice(new BeforeAdviceExample());
//        proxyFactory.addAdvice(new AfterReturningAdviceExample());
        AdviceUser userService = (AdviceUser) proxyFactory.getProxy();
        userService.test();
        userService.test2();
    }
}
