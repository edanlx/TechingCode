package com.example.demo.lesson.jvm.loader;


import java.net.URL;

public class ClassLoaderPlant {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(ClassLoaderPlant.class.getClassLoader());


        System.out.println("");

        // 获取系统ClassLoader
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        // appClassLoader的父加载器
        ClassLoader platformClassLoader = appClassLoader.getParent();
        // platformClassLoader的父加载器
        ClassLoader boostrapClassLoader = platformClassLoader.getParent();

        System.out.println("the bootstrapLoader : " + boostrapClassLoader);
        System.out.println("the extClassLoader : "+ platformClassLoader);
        System.out.println("the appClassLoader : "+ appClassLoader);
    }
}
