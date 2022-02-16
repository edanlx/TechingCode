package com.example.demo.lesson.spring.createbean.factorymethod;

public class User1 {
    /**
     * 注意这里方法static
     */
    public static User1 createUser1() {
        System.out.println("user1");
        return new User1();
    }
}
