package com.example.demo.lesson.spring.proxy.factory;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

public class FactoryExample {
    public static void main(String[] args) {
        FactoryUser target = new FactoryUser();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        // 使用如下代码会从cglib动态代理改为jdk动态代理
        // proxyFactory.setInterfaces();
        proxyFactory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("before...");
                Object result = invocation.proceed();
                System.out.println("after...");
                return result;
            }
        });
        FactoryUser userService = (FactoryUser) proxyFactory.getProxy();
        userService.test();
    }
}
