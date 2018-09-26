package com.phoenix.li.core.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

/**
 * Created by 落叶 on 2018/9/17.
 */
public class ZKNodeOperation {

  private String zkAddress;

  private CuratorFramework client = null;

  public ZKNodeOperation(String zkAddress) {
    this.zkAddress = zkAddress;
    client = CuratorFrameworkFactory.newClient(
        zkAddress, 5000, 3000,
        new RetryNTimes(5, 1000));
    client.start();
  }


  public void createData(String path) throws Exception {
    client.create().creatingParentsIfNeeded().forPath(path);
  }

  public void checkNode(String path) {

  }


}
