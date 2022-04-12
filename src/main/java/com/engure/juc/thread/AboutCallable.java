package com.engure.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/27 16:29
 *
 * * * * * * * * * * */
public class AboutCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> task = new FutureTask<>(new MyThread());
        Thread thread = new Thread(task);
        thread.start();
        Integer res = task.get();//建议放在最后
        System.out.println("get from task: " + res);

    }

}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " in call().");
        return 1024;
    }

}
