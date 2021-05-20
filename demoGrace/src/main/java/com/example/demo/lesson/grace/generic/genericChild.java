package com.example.demo.lesson.grace.generic;

public class genericChild extends genericPerent {
    @Override
    public  Integer testMethod(Object obj){
        System.out.println("Test3" + obj);
        return (Integer) obj;
    }

    @Override
    public  Integer testMethod2(Object obj){
        System.out.println("Test3" + obj);
        return (Integer) obj;
    }

    @Override
    public  Long testMethod3(Object obj){
        System.out.println("Test3" + obj);
        return (Long) obj;
    }

//    @Override
//    public <T> T testMethod(T obj){
//        System.out.println("Test3" + obj);
//        return obj;
//    }
}
