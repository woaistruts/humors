package com.phoenix.li.transport.coder.kryo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import java.io.IOException;

/**
 * Created by lilongfei on 16/3/29.
 */
public class KryoPool3 {

  private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

  private KryoFactory kryoFactory;

  public KryoPool3() {
    kryoFactory = new KryoFactory();
  }

  public KryoPool3(int maxTotal, int minIdle, int maxWaitMillis, boolean testOnBorrow) {
    kryoFactory = new KryoFactory(maxTotal, minIdle, maxWaitMillis, testOnBorrow);
  }

  public void encode(final ByteBuf out, final Object message) throws IOException {
    ByteBufOutputStream bout = new ByteBufOutputStream(out);
    bout.write(LENGTH_PLACEHOLDER);
    KryoSerialization kryoSerialization = new KryoSerialization(kryoFactory);
    kryoSerialization.serialize(bout, message);
  }

  public Object decode(final ByteBuf in) throws IOException {
    KryoSerialization kryoSerialization = new KryoSerialization(kryoFactory);
    return kryoSerialization.deserialize(new ByteBufInputStream(in));
  }
}
