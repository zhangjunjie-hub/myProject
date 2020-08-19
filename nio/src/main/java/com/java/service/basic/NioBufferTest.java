package com.java.service.basic;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * 测试nio的buffer的相关方法
 */
public class NioBufferTest {

    public static void main(String[] args) {
        basicBufferTest();
    }

    public static void basicBufferTest(){

        IntBuffer buffer = IntBuffer.allocate(10);
        for(int i = 0; i<buffer.capacity();i++){
            buffer.put(i*2);
        }
        //进行翻转处理
        buffer.flip();
        for (Integer integer: buffer.array()) {
            System.out.println(integer);
        }

    }



}
