package com.engure.juc.other.v1.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class TestLockInterruptibly {

    ReentrantLock lock = new ReentrantLock();

    void m1(){
        try {
            //如果当前线程已经持有锁，那么持有计数将增加1，该方法将立即返回。
            //如果锁被另一个线程持有，那么当前线程出于线程调度目的将被禁用，并处于休眠状态，直到获得锁，此时锁持有计数被设置为1。
            lock.lock();
            System.out.println("m1 acquired lock");
            Thread.sleep(Integer.MAX_VALUE);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //需要使用 lock.isHeldByCurrentThread()，而非 isLocked()
            if (lock.isLocked()) {//最终需要释放锁。
                //注意此方法！！！
               // If the current thread is not the holder of this lock then IllegalMonitorStateException is thrown.
               lock.unlock();
            }
        }
    }

    void m2(){
        try {
            //Acquires the lock unless the current thread is interrupted.
            //除非该线程被中断，否则就获取锁
            lock.lockInterruptibly();
            System.out.println("m2....");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TestLockInterruptibly lock = new TestLockInterruptibly();
        Thread t1 = new Thread(lock::m1);
        Thread t2 = new Thread(lock::m2);

        t1.start();
        t2.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();//中断 t2 线程

    }

}
