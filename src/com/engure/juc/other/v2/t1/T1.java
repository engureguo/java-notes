package com.engure.juc.other.v2.t1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 狂神说JUC
 * https://www.bilibili.com/video/BV1B7411L7tE
 *
 * 笔记
 * https://www.yuque.com/books/share/f928fc37-48bb-4a45-aa2a-2c200a8017f2?# 《CS知识》
 *
 */


public class T1 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();//单线程
        ExecutorService pool2 = Executors.newFixedThreadPool(5);//固定线程数
        ExecutorService pool3 = Executors.newCachedThreadPool();//可伸缩，遇强则强，遇弱则弱

        try {

            for (int i = 0; i < 100; i++) {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok!");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }


    }
}
