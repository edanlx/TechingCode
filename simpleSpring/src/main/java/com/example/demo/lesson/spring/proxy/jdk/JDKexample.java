package com.example.demo.lesson.spring.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKexample {
    public static void main(String[] args) {
        JDKUser target = new JDKUser();
        Object proxy = Proxy.newProxyInstance(JDKexample.class.getClassLoader(), new Class[]{IJDKUser.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before...jdk");
                Object result = method.invoke(target, args);
                System.out.println("after...jdk");
                return result;
            }
        });
        IJDKUser userService = (IJDKUser) proxy;
        userService.method1();
    }
}
