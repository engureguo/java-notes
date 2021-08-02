package com.engure.juc.other.v2.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/2 19:17
 *
 * * * * * * * * * * */
public class PCDemo2 {
    public static void main(String[] args) {
        MyDataCache s = new MyDataCache();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) s.increment();
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) s.decrement();
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) s.increment();
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) s.decrement();
        }, "D").start();

    }
}

/**
 * condition 简单使用
 */
class MyDataCache {

    private int num = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();//拿到锁
        try {
            while (num != 0) {//1.判断等待
                condition.await();
            }
            num++;//2.业务
            System.out.println(Thread.currentThread().getName() + " num = " + num);
            condition.signalAll();//3.唤醒
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//唤醒
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + " num = " + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
