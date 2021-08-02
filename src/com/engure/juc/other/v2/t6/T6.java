package com.engure.juc.other.v2.t6;


import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class T6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //  Returns a new CompletableFuture that is asynchronously completed by a task
        //  running in the ForkJoinPool.commonPool() after it runs the given action.
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " running..."); // 2
        });

        System.out.println(Thread.currentThread().getName() + " main"); // 1

        future.get();// Waits if necessary for this future to complete, and then returns its result.

        System.out.println("main over");
    }

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "->supplyAsync");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // int i = 1 /0;
            return 123; // 成功时可以获取(get)到的结果
        }).whenComplete((t, v) -> {
            System.out.println("t = " + t); // 正常的返回结果
            System.out.println("v = " + v); // 错误信息，java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 100; // 发生错误时可以获取(get)到的结果
        });

        System.out.println("test1 middle");

        Integer res = future.get();
        System.out.println("get() = " + res);

        System.out.println("test1 over!");
    }


}
