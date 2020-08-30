package com.example.demo.lesson.jvm.loader;

public class B {
    static {
        System.out.println("*************loadStaticB**************");
    }

    public B() {
        System.out.println("*************loadProtoTypeB**************");
    }
}
