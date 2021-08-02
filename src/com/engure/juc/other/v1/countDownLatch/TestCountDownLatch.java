package com.engure.juc.other.v1.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(100);

        try {
            //Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted.
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        latch.countDown();//计数减一


    }


}
