package com.example.demo.lesson.spring.proxy.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AfterReturningAdviceExample implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("this is" + this.getClass().getSimpleName());
    }
}
