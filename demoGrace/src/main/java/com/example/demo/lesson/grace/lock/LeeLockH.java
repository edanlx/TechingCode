package com.example.demo.lesson.grace.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class LeeLockH {
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire (int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease (int arg) {
            setState(0);
            return true;
        }
    }

    private Sync sync = new Sync();

    public void lock () {
        sync.acquire(1);
    }

    public void unlock () {
        sync.release(1);
    }


    static int count = 0;
    public static void main (String[] args) throws InterruptedException {
        LeeLockH leeLockH = new LeeLockH();
        Runnable runnable = () -> {
            try {
                leeLockH.lock();
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                leeLockH.unlock();
            }

        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
//        thread1.join();
//        thread2.join();
        Thread.sleep(1000);
        System.out.println(count);
    }
}
