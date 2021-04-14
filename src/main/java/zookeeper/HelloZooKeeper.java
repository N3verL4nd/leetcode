package zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * @author liguanghui02
 * @date 2021/4/14
 */
public class HelloZooKeeper {
    public static void main(String[] args) throws IOException {
        String hostPort = "localhost:2181";
        String zPath = "/";
        List<String> zooChildren = null;
        ZooKeeper zk = new ZooKeeper(hostPort, 2000, null);
        try {
            zooChildren = zk.getChildren(zPath, false);
            for (String child : zooChildren) {
                System.out.println(child);
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}