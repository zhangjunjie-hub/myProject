package com.java.service.service;

import com.java.service.util.DynamicBean;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 这个是一个基于NIO的群聊系统的服务器端
 * 要求：
 * 1.服务器端启动并监听指定端口
 * 2.服务器接收客户端信息，并实现消息转发(处理上线和离线的提示)
 */
public class GroupChatService {
    //定义属性
    private ServerSocketChannel listenChannel;
    private Selector selector;

   public GroupChatService(){
       try {
           //在构造方法中进行初始化操作
           //得到选择器
           selector = Selector.open();
           //得到监听的通道
           listenChannel = ServerSocketChannel.open();
           //绑定端口
           listenChannel.socket().bind(new InetSocketAddress(DynamicBean.PORT));
           //设置为非阻塞
           listenChannel.configureBlocking(false);
           //讲listenChannel注册到selector,并设置关注的事件为接收事件
           listenChannel.register(selector, SelectionKey.OP_ACCEPT);
       }catch (IOException e){
           e.printStackTrace();
       }
    }

    /**
     * 编写监听的方法
     * @param
     */
    public void listen(){
        try{
            while(true){
                //获取到监听到的通道的数量
                int count = selector.select(2000);
                //如果count大于零 说明有通道要处理
                if(count >0){
                 //获取到要监听的channel对应的key
                Set<SelectionKey> keys = selector.keys();
                //迭代获取到的selectionKey集合
                Iterator<SelectionKey> iterator = keys.iterator();
                //循环遍历SelectionKey
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    //SocketChannel channel = (SocketChannel) key.channel();
                    //监听到的是accept事件 则处理连接
                    //如果是read事件，则处理read
                    if(key.isAcceptable()){
                        //拿到socketChannel
                        SocketChannel channel = (SocketChannel)listenChannel.accept();
                        channel.configureBlocking(false);
                        channel.register(selector,SelectionKey.OP_READ);
                        //服务器端给出上线显示
                        System.out.println(channel.getRemoteAddress().toString().substring(1)+"上线");
                    }
                    //通道是可读的状态 则处理读
                    if(key.isReadable()){
                    //处理专门的read方法
                        readInfo(key);
                    //最后，手动从集合中删除已经处理过的SelectionKey，防止重复读取
                        iterator.remove();
                    }

                }

            }else{
                //没有事件的情况下，继续等待
            }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    /**
     * 读取channel中的消息
     * @param key
     */
    private void readInfo(SelectionKey key){
        SocketChannel channel = null;
        try{
            //根据SelectionKey获取到对应的SocketChannel
            channel = (SocketChannel) key.channel();
            //创建缓冲区，缓冲channel中的数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //从channel读取数据到buffer
           int count =  channel.read(buffer);
           //如果读取到数据
           if(count>0){
               //讲读取到的buffer转换成字符串
               String msg = new String(buffer.array());
               //输出该消息
               System.out.println("from 客户端："+msg);
               //向其他的客户端转发消息(发消息的channel不需要转发)
               sendMsgToOtherClients(msg,channel);
           }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 向其他的客户端转发消息
     * @param msg
     * @param self
     */
    private void sendMsgToOtherClients(String msg,SocketChannel self){
        System.out.println("服务器转发消息中……");
        //遍历所有注册到select中的socketChannel，并排除自己
        //建立缓存，存储需要发送的msg，这个数据提取出来，因为循环中公共使用的，所以转换一次就可以的
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        for (SelectionKey key : selector.keys()) {
            //通过selectionKey获取socketChannel
            Channel targetChannel = key.channel();
            //排除自己,既要保证获取的channel是socketChannel 还要保证不是自己(self)
            if(targetChannel instanceof SocketChannel && targetChannel != self){
             //讲channel转为SocketChannel
                SocketChannel dest =  (SocketChannel)targetChannel;
                try{
                  //讲buffer中的数据写入到socketChannel中
                  dest.write(buffer);
                }catch (IOException e){
                    e.printStackTrace();
                    try {
                        //捕获离线异常
                        System.out.println(((SocketChannel) targetChannel).getRemoteAddress().toString()+"离线了");
                        //取消注册
                        key.cancel();
                        //关闭对应的SocketChannel
                        targetChannel.close();
                    }catch (IOException es){
                        es.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * 服务端的启动方法
     * @param args
     */
    public static void main(String[] args) {
        GroupChatService service = new GroupChatService();
        service.listen();
    }



}
