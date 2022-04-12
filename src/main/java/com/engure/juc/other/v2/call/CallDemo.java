package com.engure.juc.other.v2.call;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/3 0:08
 *
 * * * * * * * * * * */
public class CallDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() { // 适配类
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });
        new Thread(task).start();// new Thread(Runnable).start();
        Integer i = task.get();// 可能会产生阻塞
        System.out.println(i);

    }
}
