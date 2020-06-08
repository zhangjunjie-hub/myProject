package com.java.service.client;

import com.java.service.util.DynamicBean;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 这个是模拟基于NIO的群聊系统的客户端
 * 要求：1.连接服务器
 *      2.客户端实现信息的发送消息
 *      3.接收(其他客户端发送的消息)
 */
public class GroupChatClientService {
    //定义相关属性
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;

    public GroupChatClientService(){
        //完成初始化工作
        try{
            //创建选择器
            selector = Selector.open();
            //连接服务器
            socketChannel.open(new InetSocketAddress(DynamicBean.COMMON_ADDRESS,DynamicBean.PORT));
            //设置非阻塞
            socketChannel.configureBlocking(false);
            //将socketChannel注册到selector中，设置关注的事件为OP_READ事件
            socketChannel.register(selector, SelectionKey.OP_READ);

            //获取连接名称
            userName = socketChannel.getRemoteAddress().toString().substring(1);
            System.out.println(socketChannel.getClass().hashCode());
            System.out.println(userName + " is OK……");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 向服务器发送消息
     * @param info
     */
    public void sendInfo(String info){
        info = userName + "说："+ info;
        try{
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 读取从服务器发送的消息
     */
    public void readInfo(){
        try{
            int readChannels = selector.select();
            if(readChannels>0){
                System.out.println("有可用的channel……");
                Set<SelectionKey> keys = selector.keys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int count = channel.read(buffer);
                        if(count > 0){
                            String msg = new String(buffer.array()).trim();
                            System.out.println(msg);
                        }
                    }
                }
            }else{
                //System.out.println("没有可用的channel……");
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    /**
     * 启动代码
     * @param args
     */
    public static void main(String[] args) {
     GroupChatClientService clientService = new GroupChatClientService();
     new Thread(){
         @Override
         public void run(){
             while (true) {
                 clientService.readInfo();
                 try {
                     sleep(3000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
     }.start();

     //写入数据,并发送到服务器
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String msg = scanner.nextLine();
            clientService.sendInfo(msg);
        }


    }




}
