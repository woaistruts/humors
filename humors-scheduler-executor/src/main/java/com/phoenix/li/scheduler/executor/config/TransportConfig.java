package com.phoenix.li.scheduler.executor.config;

import com.phoenix.li.transport.coder.MessageDecoder;
import com.phoenix.li.transport.coder.MessageEncoder;
import com.phoenix.li.transport.coder.kryo.KryoPool3;
import com.phoenix.li.transport.handler.HeartBeatInboundHandler;
import com.phoenix.li.transport.handler.HeartBeatOutboundHandler;
import com.phoenix.li.transport.handler.ServerInboundHandler;
import com.phoenix.li.transport.handler.ServerOutboundHandler;
import com.phoenix.li.transport.server.ServerBootStrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by 落叶 on 2018/9/16.
 */
@Configuration
@PropertySource("executor.properties")
public class TransportConfig {

  @Value("${server.port}")
  private int port;

  @Bean
  public ChannelInitializer<SocketChannel> initializer() {
    KryoPool3 kryoPool3 = new KryoPool3();
    return new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MessageDecoder(kryoPool3));
        pipeline.addLast(new MessageEncoder(kryoPool3));
        pipeline.addLast(new HeartBeatOutboundHandler());
        pipeline.addLast(new ServerOutboundHandler());
        pipeline.addLast(new ServerInboundHandler());
        pipeline.addLast(new HeartBeatInboundHandler());
      }
    };
  }

  @Bean(initMethod = "start", destroyMethod = "stop")
  public ServerBootStrap serverBootStrap() {
    return new ServerBootStrap(initializer(), port);
  }
}
