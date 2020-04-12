package com.java.bid.service;

import lombok.val;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class BioService {

    public static void main(String[] args) throws Exception {
//        int corePoolSize = 2;
//        int maximumPoolSize = 4;
//        long keepAliveTime = 10;
//        TimeUnit unit = TimeUnit.SECONDS;
//        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
//        ThreadFactory threadFactory = new NameTreadFactory();
//        RejectedExecutionHandler handler = new MyIgnorePolicy();
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
//                workQueue, threadFactory, handler);
//        executor.prestartAllCoreThreads(); // 预启动所有核心线程

        ExecutorService threadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(9999);
        while(true){

         final    Socket socket = serverSocket.accept();
            System.out.println("开始接收信息……");
           threadPool.execute(new Runnable() {
               @Override
               public void run() {

                       runMethod(socket);

               }
           });



        }





    }

    public static void  runMethod(Socket socket) {
        System.out.println(Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        InputStream in = null;
        try {
            in = socket.getInputStream();

        int count = -1;
        // BufferedReader buf =null;
        while(true) {
            //  buf = new BufferedReader(new InputStreamReader(in));
            count = in.read(bytes);
          if(count != -1){
              String temp = new String(bytes,0,count);
              System.out.println(temp);
          }else{
              break;
          }

        }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("关闭连接");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
