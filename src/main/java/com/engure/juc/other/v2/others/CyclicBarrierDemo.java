package com.engure.juc.other.v2.others;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/3 0:51
 *
 * * * * * * * * * * */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("人齐了，本小组马上出发！");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + "：我来了");

                    cyclicBarrier.await();//等待的线程满了就放行，同时执行 runnable

                    System.out.println(Thread.currentThread().getName() + "：出发了，耶耶耶！！");//业务处理

                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i+1)).start();
        }

    }
}
