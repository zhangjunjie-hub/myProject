package com.java.service.netty.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NioService {
    //定义端口
    public static final Integer PORT  = 8000;
    public static void main(String[] args) {

        new NioService().init();
    }
    public void init(){
      //定义boss和workgroup线程
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGrouo = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGrouo)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ServerInitializerService());
            ChannelFuture channelFuture = bootstrap.bind(PORT).sync();
            System.out.println("服务器正常启动……");
            channelFuture.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGrouo.shutdownGracefully();
        }

    }









}
