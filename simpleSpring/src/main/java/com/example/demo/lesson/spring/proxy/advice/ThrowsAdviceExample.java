package com.example.demo.lesson.spring.proxy.advice;

import org.springframework.aop.ThrowsAdvice;

/**
 * 格式在ThrowsAdvice内部
 * public void afterThrowing(Exception ex)
 * public void afterThrowing(RemoteException)
 * public void afterThrowing(Method method, Object[] args, Object target, Exception ex)
 */
public class ThrowsAdviceExample implements ThrowsAdvice {
    public void afterThrowing(Exception ex) {
        System.out.println("this is" + this.getClass().getSimpleName());
    }
}
