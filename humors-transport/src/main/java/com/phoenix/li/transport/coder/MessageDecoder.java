package com.phoenix.li.transport.coder;

import com.phoenix.li.transport.coder.kryo.KryoPool3;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created by 落叶 on 2018/9/10.
 */
public class MessageDecoder extends LengthFieldBasedFrameDecoder {

  private KryoPool3 kryoPool;

  public MessageDecoder(KryoPool3 kryoPool3) {
    super(10485760, 0, 4, 0, 4);
    this.kryoPool = kryoPool3;
  }

  @Override
  protected Object decode(final ChannelHandlerContext ctx, final ByteBuf in) throws Exception {
    in.readByte();
    ByteBuf frame = (ByteBuf) super.decode(ctx, in);
    if (frame == null) {
      return null;
    }
    try {
      return kryoPool.decode(frame);
    } finally {
      frame.release();
    }
  }
}
