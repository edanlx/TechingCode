package com.example.demo.lesson.spring.proxy.annon.bean.proxyfactory;

import com.example.demo.lesson.spring.proxy.annon.User;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;

public class ProxyFactoryBeanExample {
    @Bean
    public ProxyFactoryBean userProxy() {
        User user = new User();
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(user);
        proxyFactoryBean.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("before...");
                Object result = invocation.proceed();
                System.out.println("after...");
                return result;
            }
        });
        return proxyFactoryBean;
    }
}
