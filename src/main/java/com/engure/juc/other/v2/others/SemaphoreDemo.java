package com.engure.juc.other.v2.others;

import java.util.concurrent.Semaphore;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/3 1:04
 *
 * * * * * * * * * * */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {

                try {
                    System.out.println(Thread.currentThread().getName() + " 来抢车位了 1");
                    sem.acquire();

                    System.out.println(Thread.currentThread().getName() + " 抢到车位了 2");
                    try { Thread.sleep(2000); } catch (Exception e) { } finally {}

                    System.out.println(Thread.currentThread().getName() + " 把车开走了 3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    sem.release();
                }

            }, "user" + (i + 1)).start();
        }

    }
}
