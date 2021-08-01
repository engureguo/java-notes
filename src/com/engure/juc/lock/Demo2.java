package com.engure.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;


class MyData {

    private volatile static Map<String, Object> map = new HashMap<>();
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    //不同于ReentrantLock：读写都互斥
    // ReentrantReadWriteLock：读写和写写互斥，读读不互斥

    public static void add(String key, Object value) {

        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始添加");

            map.put(key, value);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 添加结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public static void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始读取");
            Object res = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取结束：" + res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().lock();
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            int tmpI = i;
            new Thread(() -> {
                add(String.valueOf(tmpI), tmpI + 1);
            }, String.valueOf(i + 1)).start();
        }

        for (int i = 0; i < 5; i++) {
            int tmpI = i;
            new Thread(() -> {
                get(String.valueOf(tmpI));
            }, String.valueOf(i + 1)).start();
        }

        /*
            模拟多用户同时进行读写
             -写操作：原子+独占
                原子：写操作不可中断
                独占：别的线程不能读不能写
             -读操作：多个线程可同时读
         */

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}


/**
 * 独占锁（写锁）/ 共享锁（写锁）/ 互斥锁
 */
public class Demo2 {
    public static void main(String[] args) {

        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    }
}
