package com.engure.juc.other.v2.others;

import java.util.concurrent.CountDownLatch;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/3 0:37
 *
 * * * * * * * * * * */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(5);

        for (int i = 1; i <= 5; i++) {
            int tmpI = i;
            new Thread(() -> {
                latch.countDown();
                System.out.println(tmpI + " over");
            }, String.valueOf(i + 1)).start();
        }

        //等待计数器归零后继续执行
        latch.await();

        System.out.println("计数器已经归零");
    }
}
