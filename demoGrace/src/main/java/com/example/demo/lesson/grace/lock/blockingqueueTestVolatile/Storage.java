package com.example.demo.lesson.grace.lock.blockingqueueTestVolatile;

import java.util.concurrent.LinkedBlockingQueue;

public class Storage {

    // 仓库存储的载体
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<>(10);

    public void produce() {
        try{
            list.put(new Object());
            if(list.size()==10){
                System.out.println("【生产者" + Thread.currentThread().getName()
                        + "】生产一个产品，现库存" + list.size());
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void consume() {
        try{
            list.take();
//            System.out.println("【消费者" + Thread.currentThread().getName()
//                    + "】消费了一个产品，现库存" + list.size());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

