package com.engure.juc.blockque;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* * * * * * * * * *
 * Description: 精确唤醒
 * Author: engure
 * Date: 2021/8/8 12:07
 *
 * * * * * * * * * * */
public class SpecificSB {
    public static void main(String[] args) {
        Data3 data = new Data3();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                data.funForA();
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                data.funForB();
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                data.funForC();
            }
        }, "C").start();
    }
}

class Data3 {

    private int num = 1;//标记该谁执行，不该某个线程执行时需要等待
    private ReentrantLock lock = new ReentrantLock();
    private Condition cond1 = lock.newCondition();//使用绑定同一lock的condition进行精确通知
    private Condition cond2 = lock.newCondition();
    private Condition cond3 = lock.newCondition();

    public void funForA() {
        lock.lock();
        try {
            while (num != 1) cond1.await();//num=1时该A执行

            num = 2;
            System.out.println(Thread.currentThread().getName() + " a");

            cond2.signalAll();//1唤醒2
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void funForB() {
        lock.lock();
        try {
            while (num != 2) cond2.await();

            num = 3;
            System.out.println(Thread.currentThread().getName() + " b");

            cond3.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void funForC() {
        lock.lock();
        try {
            while (num != 3) cond3.await();

            num = 1;
            System.out.println(Thread.currentThread().getName() + " c");

            cond1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

