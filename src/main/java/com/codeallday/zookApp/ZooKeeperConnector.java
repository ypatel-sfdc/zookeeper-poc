package com.codeallday.zookApp;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ypatel on 4/3/17.
 */

public class ZooKeeperConnector {
    private ZooKeeper zooKeeper;
    private CountDownLatch connSignal = new CountDownLatch(1);

    public ZooKeeper connect(String host) throws IOException, IllegalStateException, InterruptedException {
        zooKeeper = new ZooKeeper(host, 5000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                if(watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    connSignal.countDown();
                }
            }
        });
        connSignal.await();
        return zooKeeper;
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }
}
