package com.phoenix.li.transport.handler;

import com.phoenix.li.transport.vo.HeartBeatRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by lilongfei on 16/4/5.
 */
@Slf4j
public class ServerInboundHandler extends ChannelInboundHandlerAdapter {


  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof HeartBeatRequest) {
      ctx.fireChannelRead(msg);
    } else {
      log.debug("接收到客户端ip {} --传递的消息: {} ", ctx.channel().remoteAddress(), msg.toString());
      //System.out.println("接收到客户端ip {"+ctx.channel().remoteAddress()+"} --传递的消息: {"+msg.toString()+"} ");
//            BasicBis.doBis(msg, ctx.channel());
    }
    System.out.println(msg);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    log.info("断开连接");
//        Channel channel = ctx.channel();
//        ChannelStore channelStore = new ChannelStore();
//        channelStore.setChannel(channel);
//        channelStore.setRemoteAddress(channel.remoteAddress().toString());
//        Iterator<Map.Entry<String, ChannelStore>> it = Constant.channelMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, ChannelStore> entry = it.next();
//            if (entry.getValue().equals(channelStore)) {
//                entry.getValue().recordBreak();
//                entry.getValue().destroy();
//                it.remove();
//            }
//        }
//
    super.channelInactive(ctx);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    log.error(cause.toString());
    throw new Exception(cause.getMessage());
  }
}
