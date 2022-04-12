package com.engure.juc.lock;

import org.junit.Test;

import java.util.concurrent.*;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/3 14:20
 *
 * * * * * * * * * * */
public class CountDownLatchDemo {
    public static void main(String[] args) {

    }

    @Test
    public void countDownLatch() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " arrived!");
                latch.countDown();
            }, String.valueOf(i + 1)).start();
        }

        latch.await();//wait all
        System.out.println("new march!");

    }

    @Test
    public void cyclicBarrier() {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("OK");
        });

        for (int i = 0; i < 3; i++) {
            int tempI = i;
            new Thread(() -> {
                try {
                    //到
                    cyclicBarrier.await();
                    //放
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(tempI + 1)).start();
        }
    }

    @Test
    public void semaphore() throws InterruptedException {
        Semaphore sem = new Semaphore(3);

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    sem.acquire();
                    System.out.println(Thread.currentThread().getName() + " got permission!");
                    try { TimeUnit.SECONDS.sleep(1); } catch (Exception e) { } finally {}

                    System.out.println(Thread.currentThread().getName() + " release permission!");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sem.release();
                }
            }, String.valueOf(i + 1)).start();
        }

        TimeUnit.SECONDS.sleep(6);
    }

}
