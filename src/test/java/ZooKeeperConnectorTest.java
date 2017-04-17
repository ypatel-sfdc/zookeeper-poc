import com.codeallday.zookApp.ZooKeeperConnector;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by ypatel on 4/3/17.
 */
public class ZooKeeperConnectorTest {

    @Test
    public void testZooKeeperConnector() throws IOException, InterruptedException, KeeperException {
        ZooKeeperConnector zooKeeperConnector = new ZooKeeperConnector();
        ZooKeeper zooKeeper = zooKeeperConnector.connect("localhost");
        System.out.println(zooKeeper.getState());
        for(String s: zooKeeper.getChildren("/aiq", true)) {
            System.out.println(s);
        }
        zooKeeperConnector.close();
    }

    @Test
    public void testWithCuratorFramework() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost", new ExponentialBackoffRetry(1000, 3));

        client.start();
        System.out.println(client.getState());
        if(client.checkExists().forPath("/aiq/election") == null) {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/aiq/election");
            System.out.println(client.getChildren().forPath("/aiq"));
        }

        // Upon closing the connection the ephemeral node will be deleted by itself
        client.close();

        client = CuratorFrameworkFactory.newClient("localhost", new ExponentialBackoffRetry(1000, 3));
        client.start();
        assert (client.checkExists().forPath("/aiq/election") == null) ;
        System.out.println(client.getChildren().watched().forPath("/aiq"));
    }
}
