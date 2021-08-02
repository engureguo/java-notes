package com.engure.juc.other.v2.t8;

import com.engure.juc.other.v2.t5.RunUtil;

/*******
 * Package: com.engure.juc.other.v2.t8   
 * Description: 
 * Author: engure
 * Date: 2021/8/2 16:14
 * Modified by: 
 ******/
public class DeadLock {

    public static void main(String[] args) {

        Object obj1 = new Object();
        Object obj2 = new Object();

        new Thread(()->{
            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName() + " 获取到了 obj1 锁");

                try { Thread.sleep(2000); } catch (Exception e) { } finally {}

                System.out.println(Thread.currentThread().getName() + " 准备获取 obj2 锁");
                synchronized (obj2) {

                }

            }
        }, "thread-A").start();

        new Thread(()->{
            synchronized (obj2) {
                System.out.println(Thread.currentThread().getName() + " 获取到了 obj2 锁");

                try { Thread.sleep(2000); } catch (Exception e) { } finally {}

                System.out.println(Thread.currentThread().getName() + " 准备获取 obj1 锁");

                synchronized (obj1) {

                }

            }
        }, "thread-B").start();

    }

    /**
     * 死锁产生的原因和必要条件
     * 死锁排查
     *  1. 日志
     *  2. 堆栈 —— 使用 jps，jstack 查看异常程序堆栈
     */

}
