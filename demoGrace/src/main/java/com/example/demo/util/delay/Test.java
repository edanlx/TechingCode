package com.example.demo.util.delay;

public class Test {
    public  static void main(String[] args) {
        synchronized(new Object()){
            System.out.println(1);
        }
    }

    private  void test(String str){
        synchronized (str){
            System.out.println("hello world");
        }
    }
}
