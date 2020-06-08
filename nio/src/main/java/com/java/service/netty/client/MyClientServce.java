package com.java.service.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *  群聊客户端
 */
public class MyClientServce {
    public static final String LOCAL_ADDRESS = "127.0.0.1";
    public static final Integer PORT = 8000;

    public static void main(String[] args) throws Exception {
        new MyClientServce().start();
    }

    public void start() throws Exception{
        EventLoopGroup client = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(client)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientInitlazer());
            ChannelFuture channelFuture = bootstrap.connect(LOCAL_ADDRESS, PORT).sync();
            Channel channel = channelFuture.channel();
            System.out.println("-----"+channel.localAddress()+"---准备好了");
            //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()){
                channel.writeAndFlush(scanner.nextLine());
            }
//            while(true){
//                String msg = in.readLine();
//             channel.writeAndFlush(msg);
//            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
           client.shutdownGracefully();
        }


    }


}
