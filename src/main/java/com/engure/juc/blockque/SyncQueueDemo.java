package com.engure.juc.blockque;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/8 0:46
 *
 * * * * * * * * * * */
public class SyncQueueDemo {
    public static void main(String[] args) {

    //Creates a SynchronousQueue with nonfair access policy.
    BlockingQueue<String> syncQueue = new SynchronousQueue<>();

    new Thread(()->{

        try {
            System.out.println(Thread.currentThread().getName() + " put 1");
            syncQueue.put("1");

            System.out.println(Thread.currentThread().getName() + " put 2");
            syncQueue.put("2");//阻塞，直到 B 取出 1

            System.out.println(Thread.currentThread().getName() + " put 3");
            syncQueue.put("3");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }, "A").start();

    new Thread(()->{

        try {
            try { TimeUnit.SECONDS.sleep(2); } catch (Exception ignored) { }
            System.out.println(Thread.currentThread().getName() + " take " + syncQueue.take());

            try { TimeUnit.SECONDS.sleep(2); } catch (Exception ignored) { }
            System.out.println(Thread.currentThread().getName() + " take " + syncQueue.take());

            try { TimeUnit.SECONDS.sleep(2); } catch (Exception ignored) { }
            System.out.println(Thread.currentThread().getName() + " take " + syncQueue.take());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }, "B").start();

    }
}
