package com.engure.juc.other.v2.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* * * * * * * * * *
 * Description: 使用 Condition 实现精确唤醒（await/signal），类比之前 synchronized锁 (wait/notify)
 * Author: engure
 * Date: 2021/8/2 20:06
 *
 * * * * * * * * * * */
public class PCDemo3 {
    public static void main(String[] args) {

        MyData data = new MyData();

        new Thread(()->{
            for (int i = 0; i < 10; i++) data.turnToA();
        }, "A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) data.turnToB();
        }, "B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) data.turnToC();
        }, "C").start();

    }
}

class MyData {
    private int num = 1;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condA = lock.newCondition();
    private final Condition condB = lock.newCondition();
    private final Condition condC = lock.newCondition();

    /*
            A -> B -> C
       num  1    2    3
     */
    public void turnToA() {
        lock.lock();
        try {
            while (num != 1) condA.await();//1.判断等待
            num = 2;//2.业务
            System.out.println(Thread.currentThread().getName() + "...");
            condB.signal();//3.Wakes up one waiting thread.此题等待在 condX的线程给最多有一个
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void turnToB() {
        lock.lock();
        try {
            while (num != 2) condB.await();
            num = 3;
            System.out.println(Thread.currentThread().getName() + "...");
            condC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void turnToC() {
        lock.lock();
        try {
            while (num != 3) condC.await();
            num = 1;
            System.out.println(Thread.currentThread().getName() + "...");
            condA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
