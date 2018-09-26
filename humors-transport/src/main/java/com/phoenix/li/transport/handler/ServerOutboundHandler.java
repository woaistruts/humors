package com.phoenix.li.transport.handler;

import com.phoenix.li.transport.vo.HeartBeatResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * Created by 落叶 on 2018/9/10.
 */
public class ServerOutboundHandler extends ChannelOutboundHandlerAdapter {

  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
      throws Exception {
    if (msg instanceof HeartBeatResponse) {
      super.write(ctx, msg, promise);
    } else {
      ctx.write(msg);
      ctx.flush();
    }

  }
}
