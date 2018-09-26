package com.phoenix.li.transport.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.lang.reflect.ParameterizedType;

/**
 * Created by 落叶 on 2018/9/10.
 */
public abstract class ActionInboundHandler<T> extends ChannelInboundHandlerAdapter {

  @Override
  @SuppressWarnings("unchecked")
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    Class<T> cls = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
        .getActualTypeArguments()[0];
    if (cls.getName().equals(msg.getClass().getName())) {
      ctx.writeAndFlush(doAction((T) msg));
    } else {
      ctx.fireChannelRead(msg);
    }
  }

  public abstract Object doAction(T t);

}
