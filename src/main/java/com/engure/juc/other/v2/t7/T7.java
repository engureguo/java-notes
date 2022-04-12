package com.engure.juc.other.v2.t7;

import java.util.concurrent.TimeUnit;

public class T7 {
    /*volatile*/ static int num = 0;
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            while (num ==0){
            }
            System.out.println("num != 0");
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);

        num = 1;
        TimeUnit.SECONDS.sleep(1);

        t.join();

        System.out.println("main over");
    }
}
