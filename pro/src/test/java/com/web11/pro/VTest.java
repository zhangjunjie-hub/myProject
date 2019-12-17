package com.web11.pro;

public class VTest {
//    private  volatile static String str ;
//    private volatile static int num;
//    private volatile static boolean flag;
//
//    private static class T extends Thread{
//
//        @Override
//        public void run() {
//            //int i =0;
//            while(!flag){
//                //i++;
//                //System.out.println("i="+i);
//               Thread.yield();
//            }
//            System.out.println(num+str);
//        }
//    }
//
//    public static void main(String[] args) {
//        for (int j =0;j<1000;j++) {
//            Thread thread = new T();
//            thread.start();
//        }
//        str="aaa";
//        num=100;
//        flag=true;
//    }

//    static class Work {
//            boolean isShutDown = false;
//
//                  void shutdown() {
//                         isShutDown = true;
//                         System.out.println("shutdown!");
//                     }
//
//                  void doWork() {
//                         while (!isShutDown) {
//                                 System.out.println("doWork");
//                             }
//                     }
//     }
//
//             public static void main(String[] args) {
//                 Work work = new Work();
//                 new Thread(work::doWork).start();
//                 new Thread(work::shutdown).start();
//
//                 new Thread(work::doWork).start();
//                 new Thread(work::doWork).start();
//                 new Thread(work::shutdown).start();
//                 new Thread(work::doWork).start();
//                 new Thread(work::doWork).start();
//                 new Thread(work::doWork).start();
//             }

    static class A {
          volatile int a = 0;
          void increase() {
                         a++;
                     }
          int getA(){
                         return a;
                     }
     }

             public static void main(String[] args) {
                 A a = new A();

                 new Thread(() -> {
                         for (int i = 0;i < 1000;i++) {
                                 a.increase();
                             }
                         System.out.println(a.getA());
                     }).start();
                 new Thread(() -> {
                         for (int i = 0;i < 2000;i++) {
                                 a.increase();
                             }
                         System.out.println(a.getA());
                     }).start();
                 new Thread(() -> {
                         for (int i = 0;i < 3000;i++) {
                                 a.increase();
                             }
                         System.out.println(a.getA());
                     }).start();
                 new Thread(() -> {
                         for (int i = 0;i < 4000;i++) {
                                 a.increase();
                             }
                         System.out.println(a.getA());
                     }).start();
                 new Thread(() -> {
                         for (int i = 0;i < 5000;i++) {
                                 a.increase();
                             }
                         System.out.println(a.getA());
                     }).start();
             }
}
