package com.engure.juc.other.v1.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    public static void main(String[] args) {

        CyclicBarrier cb = new CyclicBarrier(20, ()-> System.out.println("坐好扶好，马上发车！"));

        for (int i = 0; i < 30; i++) {

            new Thread(()->{
                try {
                    cb.await();//等待所有线程到达然后继续执行

                    System.out.println("arrive.. ...");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();

        }

    }

}
