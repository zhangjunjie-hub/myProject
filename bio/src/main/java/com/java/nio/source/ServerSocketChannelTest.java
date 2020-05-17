package com.java.nio.source;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * nio中对应的serverSovketChannel
 */
public class ServerSocketChannelTest {
    public static void main(String[] args) throws Exception {
        //创建ServerSocketChannel实例
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //创建selector实例
        Selector selector = Selector.open();
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

    }



}
