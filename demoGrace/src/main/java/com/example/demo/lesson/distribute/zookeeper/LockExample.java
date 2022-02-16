package com.example.demo.lesson.distribute.zookeeper;


import lombok.SneakyThrows;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class LockExample {
    static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
    static CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", 5000, 5000, retryPolicy);

    @SneakyThrows
    public static void main(String[] args) {
        // GetLock();
        getLeaderByLock();

        getLeaderByPath();
    }

    private static void getLeaderByPath() throws Exception {
        // 与非公平锁原理一致，但为支持回调重新实现争抢最小节点
        String lockPath = "/leader";
        LeaderLatch leaderLatch = new LeaderLatch(client, lockPath);
        LeaderLatchListener listener = new LeaderLatchListener() {
            @Override
            public void isLeader() {
                System.out.println("i am master");
            }

            @Override
            public void notLeader() {
                System.out.println("i am salver");
            }
        };
        leaderLatch.addListener(listener);
        leaderLatch.start();
        leaderLatch.await();
    }

    private static void getLeaderByLock() throws InterruptedException {
        LeaderSelector leaderSelector = new LeaderSelector(client, "/disLockPath", new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                // 此处不断循环，选出新leader，所以当选出新leader后进行进行休眠,或者使用其本来的功能在执行某段逻辑后轮番成为leader
            }
        });
        // 设置后，该台机器可以再次进行选举，否则就不再次参与选举
        leaderSelector.autoRequeue();
        leaderSelector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static void GetLock() throws Exception {
        InterProcessMutex interProcessMutex = new InterProcessMutex(client, "/myLock");
        try {
            interProcessMutex.acquire();
        } catch (Exception e) {

        } finally {
            interProcessMutex.release();
        }
    }
}
