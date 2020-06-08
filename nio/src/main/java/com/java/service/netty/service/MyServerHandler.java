package com.java.service.netty.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 每当从服务端收到新的客户端连接时，
     * 客户端的 Channel 存入 ChannelGroup 列表中，
     * 并通知列表中的其他客户端 Channel
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //发送上线消息，只会对已经加入到group中的channel发送消息
        channelGroup.writeAndFlush("[client:"+channel.remoteAddress()+"]加入群聊");
        //放到服务器中进行管理
        channelGroup.add(channel);
    }

    /**
     * 每当从服务端收到客户端断开时，
     * 客户端的 Channel 自动从 ChannelGroup 列表中移除了，
     * 并通知列表中的其他客户端 Channel
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
       //获取离线的channel
        Channel channel = ctx.channel();
        //此时不需要进行group中channel的移除，在调用removed的时候，已经移除了
        //只需要发送消息给其他channel即可
        channelGroup.writeAndFlush("[client:"+channel.remoteAddress()+"]离开群聊\n");
    }

    /**
     * 每当从服务端读到客户端写入信息时，将信息转发给其他客户端的 Channel
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
         //获取到发送消息的channel
        Channel targetChannel = ctx.channel();
        for (Channel channel: channelGroup) {
            if(channel != targetChannel){
              channel.writeAndFlush("[client:"+targetChannel.remoteAddress()+"]say："+msg+"\n");
            }else{
                channel.writeAndFlush("[yourself]say："+msg+"\n");
            }
        }
    }

    /**
     *  服务端监听到客户端活动
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[系统提示：]"+channel.remoteAddress()+"目前活跃"+"\n");
        //channelGroup.writeAndFlush("[系统提示：]"+channel.remoteAddress()+"目前活跃"+"\n");
    }

    /**
     * 服务端监听到客户端不活动
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[系统提示：]"+channel.remoteAddress()+"已掉线"+"\n");
        //channelGroup.writeAndFlush("[系统提示：]"+channel.remoteAddress()+"已掉线"+"\n");
//        for (Channel ch: channelGroup) {
//            ch.writeAndFlush("[系统提示：]"+channel.remoteAddress()+"已掉线"+"\n");
//        }
    }

    /**
     *  当出现 Throwable 对象才会被调用，
     *  即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        Channel exChannel = ctx.channel();
//        System.out.println("[系统提示：]"+exChannel.remoteAddress()+"出现异常"+"\n");
//        //出现异常，关闭channel
//        exChannel.close();
        ctx.close();
    }
}
