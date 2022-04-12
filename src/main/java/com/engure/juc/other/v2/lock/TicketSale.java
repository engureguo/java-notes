package com.engure.juc.other.v2.lock;

import java.util.concurrent.locks.ReentrantLock;

/*******
 * Package: com.engure.juc.other.v2.thread   
 * Description: 改进，从 synchronized 锁改为使用重入锁
 * Author: engure
 * Date: 2021/8/2 17:52
 * Modified by: 
 ******/
public class TicketSale {

    public static void main(String[] args) {

        SaleManager saleManager = new SaleManager();

        new Thread(() -> { for (int i = 1; i < 30; i++) saleManager.sale();}, "A").start();
        new Thread(() -> { for (int i = 1; i < 30; i++) saleManager.sale();}, "B").start();
        new Thread(() -> { for (int i = 1; i < 30; i++) saleManager.sale();}, "C").start();
    }
}

class SaleManager {

    ReentrantLock lock = new ReentrantLock(true);
    int num = 60;

    public /*synchronized*/ void sale() {
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + " 买到了一张票，现在还剩" + (--num) + "张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}