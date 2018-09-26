package com.phoenix.li.transport.coder.kryo;

import com.esotericsoftware.kryo.Kryo;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by lilongfei on 16/3/29.
 */
public final class KryoFactory {

  private final GenericObjectPool<Kryo> kryoPool;

  public KryoFactory() {
    kryoPool = new GenericObjectPool<>(new PooledKryoFactory());
  }

  public KryoFactory(final int maxTotal, final int minIdle,
      final long maxWaitMillis, final boolean testOnBorrow) {
    GenericObjectPoolConfig config = new GenericObjectPoolConfig();
    config.setMaxTotal(maxTotal);
    config.setMinIdle(minIdle);
    config.setMaxWaitMillis(maxWaitMillis);
    config.setTestOnBorrow(testOnBorrow);
    kryoPool = new GenericObjectPool<>(new PooledKryoFactory(), config);
  }

  public Kryo getKryo() {
    try {
      return kryoPool.borrowObject();
    } catch (final Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public void returnKryo(final Kryo kryo) {
    kryoPool.returnObject(kryo);
  }
}
