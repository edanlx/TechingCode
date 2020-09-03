package com.example.demo.lesson.jvm.myloader;

import java.io.FileInputStream;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/9/1 7:23 PM
 * @description
 */
public class MyClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        // Class clazz1 = new MyClassLoader().loadClass("com.example.demo.lesson.jvm.loader.A",false);
        //Class clazz1 = new MyClassLoader().loadClass("java.lang.Byte",false);
        Class clazz2 = new MyClassLoader2().loadClass("com.example.demo.lesson.jvm.loader.A",false);
        System.out.println(new MyClassLoaderDemo().getClass().getClassLoader());
        // System.out.println(clazz1.getClassLoader());
        System.out.println(clazz2.getClassLoader());
    }

    public static String classPath = "F:\\IdeaProjects\\TechingCode\\demoGrace\\src\\main\\java";
    private static byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name
                + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

    static class MyClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();
                    if (name.startsWith("java.lang.Byte")) {
                        c = findClass(name);
                    } else {
                        c = this.getParent().loadClass(name);
                    }
                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        long t1 = System.nanoTime();
                        c = findClass(name);

                        // this is the defining class loader; record the stats
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
    }

    static class MyClassLoader2 extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();
                    if (name.startsWith("com.example.demo.lesson")) {
                        c = findClass(name);
                    } else {
                        c = this.getParent().loadClass(name);
                    }
                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        long t1 = System.nanoTime();
                        c = findClass(name);

                        // this is the defining class loader; record the stats
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
    }
}
