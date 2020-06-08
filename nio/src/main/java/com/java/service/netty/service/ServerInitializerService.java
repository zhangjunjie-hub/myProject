package com.java.service.netty.service;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServerInitializerService extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
         //pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
        //向pipeline中添加解码器
        pipeline.addLast("decoder",new StringDecoder())
                //向pipeline加入编码器
                 .addLast("encoder",new StringEncoder())
                //加入业务handler
                 .addLast("myHandler",new MyServerHandler());
               System.out.println("[client:"+socketChannel.remoteAddress()+"]连接上了");
    }
}
