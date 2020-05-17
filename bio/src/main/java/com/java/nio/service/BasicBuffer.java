package com.java.nio.service;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {

        IntBuffer buffer = IntBuffer.allocate(10);
        for(int i = 0;i<buffer.capacity(); i++){
            buffer.put(97+i);
        }
        buffer.flip();//读写切换
        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
            //转换成char类型
            System.out.println((char)buffer.get());
        }

    }




}
