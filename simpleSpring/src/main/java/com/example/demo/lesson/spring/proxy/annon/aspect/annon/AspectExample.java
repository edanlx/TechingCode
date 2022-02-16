package com.example.demo.lesson.spring.proxy.annon.aspect.annon;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
// EnableAspectJAutoProxy可以写在config类上
@EnableAspectJAutoProxy
public class AspectExample {

    @Before("execution(public void com.example.demo.lesson.spring.proxy.annon.User.test())")
    public void myAspect(JoinPoint joinPoint) {
        System.out.println("before");
    }

    @Before(value = "execution(public void com.example.demo.lesson.spring.proxy.annon.User.test(..)) && args(arg1,arg2)", argNames = "arg1,arg2")
    public void myAspect2(JoinPoint joinPoint, String arg1, String arg2) {
        System.out.println("this is arg1" + arg1);
        System.out.println("this is arg2" + arg2);

    }
}
