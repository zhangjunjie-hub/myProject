package com.web11.pro;

import java.util.concurrent.*;

public class Test implements Runnable {

    @Override
    public void run() {
        System.out.println("asfsad");
    }
}

class TestA implements Callable{


    @Override
    public Object call() throws Exception {
        Thread.sleep(5000);
        return "adfadf";
    }
}


class TestB extends  Thread{

    @Override
    public void run() {

        for (int i=0;i<1000;i++){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }



    }
}