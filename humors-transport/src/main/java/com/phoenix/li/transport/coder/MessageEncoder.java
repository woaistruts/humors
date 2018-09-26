package com.phoenix.li.transport.coder;

import com.phoenix.li.transport.coder.kryo.KryoPool3;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by 落叶 on 2018/9/10.
 */
public class MessageEncoder extends MessageToByteEncoder<Object> {

  private KryoPool3 kryoPool3;

  public MessageEncoder(KryoPool3 kryoPool3) {
    this.kryoPool3 = kryoPool3;
  }

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf)
      throws Exception {

    int startIdx = byteBuf.writerIndex();
    kryoPool3.encode(byteBuf, o);
    int endIdx = byteBuf.writerIndex();
    byteBuf.setInt(startIdx, endIdx - startIdx - 4);
  }
}
