package com.java.service.basic;


import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 测试mappedBuffer的相关属性方法
 * MappedByteBuffer 可以让文件在堆外内存中修改
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception {
        bufferTest();
    }

    public static void bufferTest() throws Exception {

        RandomAccessFile file = new RandomAccessFile("D:/rootParent/source/1.txt","rw");
        MappedByteBuffer map = file.getChannel().map(FileChannel.MapMode.READ_WRITE,0,file.length());
        map.put(0,(byte)'W');
        map.put(10,(byte)'Q');
        file.close();
    }


}
