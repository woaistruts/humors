package com.phoenix.li.core.zk;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by 落叶 on 2018/9/17.
 */
public class TestZk {

  private static final String ZK_ADDRESS = "localhost:2181";
  private static final String ROOT_PATH = "/humors";
  private ZKNodeOperation operation = null;

  @Before
  public void createConn() {
    operation = new ZKNodeOperation(ZK_ADDRESS);
  }

  @Test
  public void testCreateNode() throws Exception {
    System.out.println(operation.createData("/humors"));
  }
}
