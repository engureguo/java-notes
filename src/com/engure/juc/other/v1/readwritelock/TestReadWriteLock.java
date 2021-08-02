package com.engure.juc.other.v1.readwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {
//    static ReentrantLock lock = new ReentrantLock();

    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    static int count=0;

    static void read(Lock lock){
        try {
            lock.lock();
            Thread.sleep(500);
            System.out.println("read.. ..");
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    static void write(Lock lock){
        try {
            lock.lock();
            Thread.sleep(500);
            System.out.println("write.. ..");
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        Runnable readR = () -> read(readLock);//可共享
        Runnable writeR = () -> write(writeLock);//不可共享

        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(writeR).start();
        }

    }

}
