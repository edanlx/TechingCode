package com.example.demo.lesson.grace.generic;

public class genericPerent {
    public <T> T testMethod(T obj) {
        System.out.println("Test4" + obj);
        return obj;
    }

    public Object testMethod2(Object obj) {
        System.out.println("Test4" + obj);
        return obj;
    }

    public Number testMethod3(Object obj) {
        System.out.println("Test4" + obj);
        return (Number) obj;
    }

}
