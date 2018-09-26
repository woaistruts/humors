package com.phoenix.li.transport.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by 落叶 on 2018/9/10.
 */
public class ClientBootStrap {

  private EventLoopGroup group;
  private ChannelInitializer<SocketChannel> initializer;
  private String ip;
  private int port;

  public ClientBootStrap(
      ChannelInitializer<SocketChannel> initializer, String ip, int port) {
    this.initializer = initializer;
    this.ip = ip;
    this.port = port;
  }

  public ChannelFuture start() {
    group = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap()
        .group(group)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .handler(initializer);
    ChannelFuture future = bootstrap.connect(ip, port);

    return future;
  }

  public void stop() {
    group.shutdownGracefully();
  }
}
