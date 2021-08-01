package com.engure.atomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写一个自旋锁
 */
public class MyCASLock {

    private static AtomicReference<Thread> atomicReference = new AtomicReference<>();//默认引用为null（Thread类型）

    public static void getLock() {
        Thread curThread = Thread.currentThread();
        System.out.println(curThread.getName() + " 尝试获取锁");
        while (!atomicReference.compareAndSet(null, curThread)) {

        }
        System.out.println(curThread.getName() + " 拿到了锁");
    }

    public static void releaseLock() {
        atomicReference.compareAndSet(Thread.currentThread(), null);
        System.out.println(Thread.currentThread().getName() + " 释放了锁");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                getLock();

                // 模拟业务
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                releaseLock();
            }, "thread-" + i).start();

        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
