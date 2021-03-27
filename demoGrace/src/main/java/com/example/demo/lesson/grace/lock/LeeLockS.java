package com.example.demo.lesson.grace.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * tryAcquireShared 正数表示获取成功，还有剩余资源
 *
 * @author 876651109@qq.com
 * @date 2021/3/26 8:25 下午
 */
public class LeeLockS implements Lock {
    private static class Syn extends AbstractQueuedSynchronizer {
        public Syn(int count) {
            setState(count);
        }

        /**
         * 拿锁
         *
         * @param arg
         * @return
         */
        @Override
        protected int tryAcquireShared(int arg) {
            for (; ; ) {
                int current = getState();
                int newCount = current - arg;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int current = getState();
                int newCount = current + arg;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }

        /**
         * 如果需要用到await和sing的时候需要用到
         *
         * @return
         */
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private static Syn syn = new Syn(3);

    @Override
    public void lock() {
        syn.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        syn.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return syn.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return syn.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        syn.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return syn.newCondition();
    }

    public static void main(String[] args) {
        LeeLockS myShareLock = new LeeLockS();
        for (int i = 0; i < 50; i++) {
            new Thread() {
                @Override
                public void run() {
                    myShareLock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "执行go");
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        myShareLock.unlock();
                    }
                }
            }.start();
        }
    }
}
