package com.example.demo.lesson.jvm.concurrence;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/11/22 3:20 PM
 * @description
 */
public class Concurrence {
    public boolean v = false;

    public static void main(String[] args) {
        Concurrence c = new Concurrence();
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            c.v = true;
            System.out.println(c.v);
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                if (c.v) {
                    System.out.println("hello");
                }
            }
        });
        thread2.start();
        thread1.start();
        while (true) {

        }
    }
}
