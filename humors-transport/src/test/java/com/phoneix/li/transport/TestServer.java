package com.phoneix.li.transport;

import com.phoenix.li.transport.coder.MessageDecoder;
import com.phoenix.li.transport.coder.MessageEncoder;
import com.phoenix.li.transport.coder.kryo.KryoPool3;
import com.phoenix.li.transport.handler.HeartBeatInboundHandler;
import com.phoenix.li.transport.handler.HeartBeatOutboundHandler;
import com.phoenix.li.transport.handler.ServerInboundHandler;
import com.phoenix.li.transport.handler.ServerOutboundHandler;
import com.phoenix.li.transport.server.ServerBootStrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;


/**
 * Created by 落叶 on 2018/9/16.
 */
public class TestServer {

  private static final Logger logger = LoggerFactory.getLogger(TestServer.class);

  private ChannelInitializer<SocketChannel> initializer;

  @Before
  public void prepare() {
    KryoPool3 kryoPool3 = new KryoPool3();
    initializer = new ChannelInitializer<SocketChannel>() {
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

  @Test
  public void test() throws Exception {

    ServerBootStrap serverBootStrap = new ServerBootStrap(initializer, 12581);
    serverBootStrap.start();
  }
}
