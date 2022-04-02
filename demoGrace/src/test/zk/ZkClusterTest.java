package zk;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ZkClusterTest {

    private static final String CLUSTER_CONNECT_STR = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";

    private static final Integer SESSION_TIMEOUT = 60 * 1000;

    private static ZooKeeper zooKeeper = null;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ObjectMapper objectMapper = new ObjectMapper();

    // 初始化zk
    public void init() throws IOException {
        log.info("try to connect to zk server");
        zooKeeper = new ZooKeeper(CLUSTER_CONNECT_STR, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 根据事件类型判断是否连接成功
                if (event != null && event.getType().equals(Event.EventType.None)
                        && event.getState().equals(Event.KeeperState.SyncConnected)) {
                    // 当连接建立好之后
                    countDownLatch.countDown();
                    log.info("zk 连接成功...");
                }
            }
        });
    }


    @Test
    public void testReconnect() throws InterruptedException, IOException, KeeperException {
        init();
        // 不断的去拿这个节点的数据
        String path = zooKeeper.create("/test", "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        log.info(path);
        while (true) {
            Stat stat = new Stat();

            try {
                byte[] data = zooKeeper.getData("/zookeeper", false, stat);
                log.info("get data: {}", new String(data));
                // 每隔5s拉取一次数据
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                log.error("数据获取异常： {}", e.getMessage());
                log.info("开始重连...");
                while (true) {
                    log.info("zookeeper status: {}", zooKeeper.getState().name());
                    if (zooKeeper.getState().isConnected()) {
                        break;
                    }
                    TimeUnit.SECONDS.sleep(3);
                }
            }
        }
    }
}
