//package com.example.demo.lesson.jvm.loader;
//
//import sun.misc.Launcher;
//
//import java.net.URL;
//
//public class ClassLoaderExe {
//    public static void main(String[] args) {
//        // 核心rt.jar中的类加载器 是C++加载的，因此这里为null
//        System.out.println(String.class.getClassLoader());
//        // 扩展包的加载器 ExtClassLoader
//        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());
//        // 应用加载器 AppClassLoader
//        System.out.println(ClassLoaderExe.class.getClassLoader());
//
//
//        System.out.println("");
//
//        // 获取系统ClassLoader
//        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
//        // appClassLoader的父加载器
//        ClassLoader extClassLoader = appClassLoader.getParent();
//        // extClassLoader的父加载器
//        ClassLoader boostrapClassLoader = extClassLoader.getParent();
//
//        System.out.println("the bootstrapLoader : " + boostrapClassLoader);
//        System.out.println("the extClassloader : " + extClassLoader);
//        System.out.println("the appClassLoader : "+ appClassLoader);
//
//        System.out.println("==============bootstrapLoader加载的文件====================");
//
//
//        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
//        for (int i = 0; i < urLs.length; i++) {
//            System.out.println(urLs[i]);
//        }
//        System.out.println("");
//
//
//        System.out.println("==============extClassloader加载的文件====================");
//        System.out.println(System.getProperty("java.ext.dirs"));
//
//        System.out.println("");
//
//
//        System.out.println("==============appClassLoader 加载的文件====================");
//        System.out.println(System.getProperty("java.class.path"));
//    }
//}
