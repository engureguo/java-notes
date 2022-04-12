package com.engure.juc.other.v2.lock.lockwhat;

import java.util.concurrent.TimeUnit;

/* * * * * * * * * *
 * Description: 
 * Author: engure
 * Date: 2021/8/2 23:02
 *
 * * * * * * * * * * */
public class LockWho5 {
    public static void main(String[] args) throws InterruptedException {

        ENTRY5 entry = new ENTRY5();

        new Thread(()->{
            try {
                entry.a();//相当于ENTRY5.a()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(entry::b).start();
    }
}

class ENTRY5 {
    public static synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);//sleep不放锁
        System.out.println("a");
    }

    public synchronized void b() {
        System.out.println("b");
    }
}
