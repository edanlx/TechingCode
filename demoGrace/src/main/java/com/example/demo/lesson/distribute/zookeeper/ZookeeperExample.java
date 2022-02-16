package com.example.demo.lesson.distribute.zookeeper;

import lombok.SneakyThrows;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZookeeperExample {
    @SneakyThrows
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.None && event.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                }
            }
        });
        // TODO 集群Curator示例
        // 集群、Cur
        countDownLatch.await();
    }
}
