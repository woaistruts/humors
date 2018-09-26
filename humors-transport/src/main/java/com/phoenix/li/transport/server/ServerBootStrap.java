package com.phoenix.li.transport.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by 落叶 on 2018/9/10.
 */
@Slf4j
public class ServerBootStrap {

  private ChannelInitializer<SocketChannel> initializer;

  public ServerBootStrap(ChannelInitializer<SocketChannel> initializer, int port) {
    this.initializer = initializer;
    this.port = port;
  }

  private EventLoopGroup bossGroup;
  private EventLoopGroup workerGroup;
  private int port;
  private ChannelFuture future;

  public void start() throws InterruptedException {
    bossGroup = new NioEventLoopGroup(1);
    workerGroup = new NioEventLoopGroup(2);
    ServerBootstrap b = new ServerBootstrap();
    b.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .childHandler(initializer)
        .option(ChannelOption.SO_BACKLOG, 512)
        .option(ChannelOption.TCP_NODELAY, true)
        .childOption(ChannelOption.SO_KEEPALIVE, true);
    future = b.bind(port).sync();
    log.info("start server at port: {}", port);
  }

  public void stop() {
    bossGroup.shutdownGracefully();
    workerGroup.shutdownGracefully();
  }

  public ChannelFuture getFuture() {
    return future;
  }
}
