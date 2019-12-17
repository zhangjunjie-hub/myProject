package com.web11.pro;


import java.util.concurrent.*;

public class MainTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        BlockingQueue<Runnable> workQueue = null;
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
//                20,10000, TimeUnit.MINUTES,workQueue);
        ExecutorService executorService = null;
        //创建可以缓存线程的线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则创建新的线程
         executorService = Executors.newCachedThreadPool();
        for(int i=0;i<100;i++){
            final int index=i ;
            Thread.sleep(100);
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println("第" +index +"个线程" +Thread.currentThread().getName());
                }
            });
        }



        //System.out.println(Runtime.getRuntime().availableProcessors());
        //创建固定长度的线程池，可控制最大并发数，超出线程航渡，则会在队列中等待
//        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        for(int i=0;i<100;i++){
//            final int index=i ;
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("第" +index +"个线程" +Thread.currentThread().getName());
//                }
//            });
//        }
          //创建定长，支持周期调用的线程池。
//          ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("sadfas");
//            }
//        },2,2,TimeUnit.SECONDS);
//         scheduledExecutorService.shutdown();
        //创建只有一个线程的线程池，只会用唯一的工作线程来执行任务，保证任务按照制定顺序执行。
//        executorService = Executors.newSingleThreadExecutor();
//        for(int i=0;i<100;i++){
//            final int index=i ;
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("第" +index +"个线程" +Thread.currentThread().getName());
//                }
//            });
//        }
        executorService.shutdown();






        //        threadPoolExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("asdfasdf");
//            }
//        });
//        threadPoolExecutor.shutdown();
//        threadPoolExecutor.submit();

        //通过继承Thread类的方式实现多线程
//        Thread td = new TestB();
//        ReentrantLock r = new ReentrantLock();
//        r.lock();
        //td.sleep(1000);
//        td.yield();
//        td.setDaemon(true);
//        td.start();
//        td.join();
//        td.wait();


        //通过实现Runnable接口的方式实现多线程
//        Thread thread = new Thread(new Test());
//        thread.start();
//        //通过实现Callable的方式实现多线程
//        FutureTask futureTask = new FutureTask(new TestA());
//        Thread thread1 = new Thread(futureTask);
//        thread1.start();
//        Object o = futureTask.get();
//        System.out.println(o);


       //yieldDemo ms = new yieldDemo();
//        Thread t1 = new Thread(ms,"张三吃完还剩");
//        Thread t2 = new Thread(ms,"李四吃完还剩");
//        Thread t3 = new Thread(ms,"王五吃完还剩");
//        t1.start();
//        t1.join();
//
//        t2.start();
//        t3.start();
//        System.out.println( "主线程");
    }




}
