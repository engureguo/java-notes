package com.engure.juc.blockque;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* * * * * * * * * *
 * Description: 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，来 5 轮
 * Author: engure
 * Date: 2021/8/8 11:17
 *
 * * * * * * * * * * */
public class Q1 {
    public static void main(String[] args) {

        Data1 data = new Data1();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    data.increament();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    data.decreament();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

    }
}

class Data1 {
    private int num = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    public void increament() throws InterruptedException {

        lock.lock();//加锁，同步代码块，同sync
        try {

            while (num != 0)//判断，多线程条件下注意虚假唤醒
                cond.await();

            num++;//业务
            System.out.println(Thread.currentThread().getName() + " num = " + num);

            cond.signalAll();//通知唤醒

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decreament() throws InterruptedException {

        lock.lock();
        try {

            while (num == 0)
                cond.await();

            num--;
            System.out.println(Thread.currentThread().getName() + " num = " + num);

            cond.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
