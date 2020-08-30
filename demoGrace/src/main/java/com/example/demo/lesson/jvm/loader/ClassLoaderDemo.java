package com.example.demo.lesson.jvm.loader;

/**
 * 演示ClassLoader加载机制
 *
 * @author seal 876651109@qq.com
 * @date 8/4/2020 12:33 AM
 */
public class ClassLoaderDemo {
    static {
        System.out.println("*************loadParent**************");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("*************loadMain**************");
        B b = null;
    }
}
