package com.example.demo.lesson.spring.proxy.cglib;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class CglibExample {
    public static void main(String[] args) {
        // 基于父子类，被代理的类是父类
        CglibUser target = new CglibUser();
        // 通过cglib技术
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibUser.class);
        // 定义额外逻辑，也就是代理逻辑
        // o 被代理对象/原始对象
        // methodProxy 代理对象
        // method、objects反射相关对象
        // 此处可以定义多个拦截器,但是如果定义多个则需要额外实现callbackFilter
        enhancer.setCallbacks(new Callback[]{
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    System.out.println("before...0");
                    Object result = methodProxy.invoke(target, objects);
                    System.out.println("after...0");
                    return result;
                },
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    System.out.println("before...1");
                    Object result = methodProxy.invokeSuper(o, objects);
                    System.out.println("after...1");
                    return result;
                }
        });
        // 返回上述下标即0/1
        enhancer.setCallbackFilter((method) -> RandomUtils.nextInt(0, 2));
        CglibUser cglibUser = (CglibUser) enhancer.create();
        cglibUser.method1();
    }
}
