package com.example.demo.lesson.jvm.concurrence;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/11/22 3:20 PM
 * @description
 */
@Slf4j
public class ConcurrenceObj {
    public volatile List<Boolean> list = new ArrayList<Boolean>(){{
        add(false);
    }};

    public static void main(String[] args) {
        ConcurrenceObj c = new ConcurrenceObj();
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            c.list.set(0,true);
            System.out.println(c.list.get(0));
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                c.judge();
            }
        });
        thread2.start();
        thread1.start();
        while (true) {

        }
    }
    private void judge(){
        if (list.get(0)) {
            System.out.println("hello");
        }
    }
}
