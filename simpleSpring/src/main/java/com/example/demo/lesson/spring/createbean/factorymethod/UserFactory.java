package com.example.demo.lesson.spring.createbean.factorymethod;

public class UserFactory {

    /**
     * 注意这里方法不能static
     */
    public User2 createUser2() {
        System.out.println("user2");
        return new User2();
    }
}
