package com.example.demo.util.delay;

import java.util.function.Supplier;

public class Delay {
    public static <T> T showLog(int level, Supplier<T> method) {
        if (level == 1) {
            return method.get();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        showLog(1,()->{
            System.out.println(1);
            return 1;
        });
        showLog(2,()->{
            System.out.println(2);
            return 1;
        });
    }
}
