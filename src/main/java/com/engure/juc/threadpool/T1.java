package com.engure.juc.threadpool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/27 17:27
 *
 * * * * * * * * * * */
public class T1 {
    public static void main(String[] args) throws IOException {
        //System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newFixedThreadPool(3);
        ExecutorService executorService3 = Executors.newCachedThreadPool();

    }
}
