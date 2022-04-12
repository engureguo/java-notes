package com.engure.juc.blockque;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/* * * * * * * * * *
 * Description: 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，来 5 轮
 * Author: engure
 * Date: 2021/8/8 11:40
 *
 * * * * * * * * * * */
public class Q2 {
    public static void main(String[] args) {
        Data2 d = new Data2();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    d.increament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    d.decreament();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}

class Data2 {

    private BlockingQueue<Integer> blockingQueue = new SynchronousQueue<>();

    public void increament() throws Exception {

        blockingQueue.put(1);

    }

    public void decreament() throws Exception {

        blockingQueue.take();

    }


}
