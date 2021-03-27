package com.example.demo.lesson.grace.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class ParkAndIntertuper {

    /**
     * park() {
     *     if(permit > 0) {
     *         permit = 0;
     *         return;
     *     }
     *
     *     if(中断状态 == true) {
     *         return;
     *     }
     *
     *     阻塞当前线程;  // 将来会从这里被唤醒
     *
     *     if(permit > 0) {
     *         permit = 0;
     *     }
     * }
     *
     * unpark(Thread thread) {
     *     if(permit < 1) {
     *         permit = 1;
     *         if(thread处于阻塞状态)
     *             唤醒线程thread;
     *     }
     * }
     *
     * interrupt(){
     *     if(中断状态 == false) {
     *         中断状态 = true;
     *     }
     *     unpark(this);    //注意这是Thread的成员方法，所以我们可以通过this获得Thread对象
     * }
     *
     * 总结调用interrupt后无法阻塞
     * 需要调用interrupted才可以park
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // test1();
        // test2();
        // test3();
        // test4();
//         test5();
        // test6();
    }


    private static void test1() {
        //因为此时permit为0且中断状态为false，所以阻塞
        LockSupport.park();
    }
    private static void test2() {
        //置permit为1
        LockSupport.unpark(Thread.currentThread());
        //消耗掉permit后，直接返回了
        LockSupport.park();
    }

    private static void test3() {
        LockSupport.unpark(Thread.currentThread());
        //消耗掉permit后，直接返回了
        LockSupport.park();
        //此时permit为0，中断状态为false，必然会阻塞
        LockSupport.park();
    }

    private static void test4() {
        // 中断标记改变一直无法阻塞
        Thread.currentThread().interrupt();
        LockSupport.park();
        LockSupport.park();
        LockSupport.park();
    }

    private static void test5() {
        // boolean interrupted1 = Thread.currentThread().isInterrupted();
        // log.info(interrupted1+"");
        Thread.currentThread().interrupt();
        boolean interrupted2 = Thread.currentThread().isInterrupted();
        log.info(interrupted2+"");
        boolean interrupted3 = Thread.currentThread().isInterrupted();
        log.info(interrupted3+"");
        LockSupport.park();
        LockSupport.park();
        LockSupport.park();
        boolean interrupted4 = Thread.currentThread().isInterrupted();
        log.info(interrupted4+"");
    }
    private static void test6() {
        // boolean interrupted1 = Thread.currentThread().isInterrupted();
        // log.info(interrupted1+"");
        Thread.currentThread().interrupt();
        boolean interrupted2 = Thread.interrupted();
        log.info(interrupted2+"");
        boolean interrupted3 = Thread.interrupted();
        log.info(interrupted3+"");
        // 被阻塞
        LockSupport.park();
        LockSupport.park();
        LockSupport.park();
        boolean interrupted4 = Thread.interrupted();
        log.info(interrupted4+"");
    }





}
