package com.example.demo.lesson.jvm.myloader;

import java.io.FileInputStream;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/9/1 7:23 PM
 * @description
 */
public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = new MyClassLoader().loadClass("com.example.demo.lesson.jvm.loader.A",false);
        System.out.println(clazz.getClassLoader());
    }

    private String classPath = "/Users/seal/IdeaProjects/TeachingCode/TechingCode/demoGrace/src/main/java";

    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name
                + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

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
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
}
