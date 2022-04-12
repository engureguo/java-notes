package com.engure.juc.other.v2.thread;

import org.junit.Test;

/*******
 * Package: com.engure.juc.other.v2.thread   
 * Description: 
 * Author: engure
 * Date: 2021/8/2 16:55
 * Modified by: 
 ******/
public class T1 {
    public static void main(String[] args) {

        new Thread().start();//是java创建的线程吗？不是！

        System.out.println("CPU核心数" + Runtime.getRuntime().availableProcessors());

    }

    @Test
    public void test() {

        Object obj = new Object();

        new Thread(()->{

            synchronized (obj) {

                try {
                    System.out.println(Thread.currentThread().getName() + " 小j还没来，我等他一会吧");
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + " 哈喽~ 我们走吧");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }, "thread-1").start();

        new Thread(()->{

            // 模拟 thread-1 先到（先拿到锁）
            try { Thread.sleep(2000); } catch (Exception e) { } finally {}

            synchronized (obj) {

                System.out.println(Thread.currentThread().getName() + " 哈喽~");
                obj.notifyAll();
                System.out.println(Thread.currentThread().getName() + " 久等了，朋友，辛苦辛苦！");

            }

        }, "thread-2").start();


        try { Thread.sleep(5000); } catch (Exception e) { } finally {}

    }




}
