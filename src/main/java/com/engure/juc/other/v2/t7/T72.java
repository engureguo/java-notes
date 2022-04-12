package com.engure.juc.other.v2.t7;

/**
 * 验证 volatile 不保证原子性
 */
public class T72 {

    static volatile int sum = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i=0; i<10000; i++) sum++;
        });
        Thread t2 = new Thread(()->{
            for (int i=0; i<10000; i++) sum++;
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(sum); // 13208
    }

}
