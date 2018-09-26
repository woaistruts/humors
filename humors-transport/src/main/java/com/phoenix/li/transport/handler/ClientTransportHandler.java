package com.phoenix.li.transport.handler;

import com.phoenix.li.transport.vo.HeartBeatRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by 落叶 on 2018/9/16.
 */
public class ClientTransportHandler extends SimpleChannelInboundHandler<Object> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      IdleStateEvent event = (IdleStateEvent) evt;
      if (event.state().equals(IdleState.READER_IDLE)) {
      } else if (event.state().equals(IdleState.WRITER_IDLE)) {
      } else if (event.state().equals(IdleState.ALL_IDLE)) {
        ctx.channel().writeAndFlush(new HeartBeatRequest());
      }
    } else {
      super.userEventTriggered(ctx, evt);
    }
  }
}
