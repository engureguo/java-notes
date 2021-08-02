package com.engure.juc.other.v1.reentrantlock;

public class TestReentrantlock {

    synchronized void m1() {

        m2();//同一个线程，调用m2，不会再次申请锁。否则会造成死锁。

    }

    synchronized void m2() {
        ///...
    }

    public static void main(String[] args) {
        /*ReentrantLock lock = new ReentrantLock();
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

}
