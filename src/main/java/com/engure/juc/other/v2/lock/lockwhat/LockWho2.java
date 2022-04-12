package com.engure.juc.other.v2.lock.lockwhat;

import java.util.concurrent.TimeUnit;

/* * * * * * * * * *
 * Description: 锁对象
 * Author: engure
 * Date: 2021/8/2 23:02
 *
 * * * * * * * * * * */
public class LockWho2 {
    public static void main(String[] args) throws InterruptedException {

        ENTRY2 entry = new ENTRY2();

        new Thread(()->{
            try {
                entry.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(entry::b).start();
    }
}

class ENTRY2 {
    public synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);//sleep不放锁
        System.out.println("a");
    }

    public synchronized void b() {
        System.out.println("b");
    }
}
