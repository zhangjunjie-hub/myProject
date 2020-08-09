package com.java.service.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BasicTest {

    public static void main(String[] args) throws Exception {
        //socketchannel
        //changeFileInfo();

        //文件复制
        copyPicture("D:/rootParent/source/1.png","D:/rootParent/source/2.png");



    }

    /**
     * 使用输入输出流中的socketchannel来更改复制文件内容
     * @throws Exception
     */
   public static void changeFileInfo() throws Exception{
       File file = new File("D:/rootParent/source/1.txt");
       RandomAccessFile in = new RandomAccessFile(file,"rws");
       FileChannel channel = in.getChannel();
       String str = "asdfasdfasfffffffffffffffffffffffffffffffffffffffffffffff";
       ByteBuffer buffer = ByteBuffer.allocate(str.getBytes().length);
       buffer.put(str.getBytes());
       buffer.flip();
       channel.write(buffer);
       in.close();
   }

    /**
     * 使用socketchannel复制文件
     * @param sourceAddr 源文件地址
     * @param targetAddr 复制到指定的地址
     */
   public static void copyPicture(String sourceAddr,String targetAddr) throws Exception{

       FileInputStream in = new FileInputStream(sourceAddr);
       FileChannel sourceChannel = in.getChannel();
       FileOutputStream out = new FileOutputStream(targetAddr);
       FileChannel targetChannel = out.getChannel();
       targetChannel.transferFrom(sourceChannel,0,sourceChannel.size());
       in.close();
       out.close();
   }







}
