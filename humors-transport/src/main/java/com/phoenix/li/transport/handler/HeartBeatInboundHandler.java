package com.phoenix.li.transport.handler;

import com.phoenix.li.transport.vo.HeartBeatRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by 落叶 on 2018/9/10.
 */
@Slf4j
public class HeartBeatInboundHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    HeartBeatRequest request = new HeartBeatRequest();
    log.trace("heart beat message : {}" + request.toString());
    ctx.channel().writeAndFlush(new HeartBeatRequest());
  }
}
