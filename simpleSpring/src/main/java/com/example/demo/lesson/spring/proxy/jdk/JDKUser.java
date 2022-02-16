package com.example.demo.lesson.spring.proxy.jdk;

public class JDKUser implements IJDKUser{
    @Override
    public void method1() {
        System.out.println("this.is JDK");
    }
}
