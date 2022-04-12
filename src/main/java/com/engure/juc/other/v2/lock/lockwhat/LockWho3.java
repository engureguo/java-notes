package com.engure.juc.other.v2.lock.lockwhat;

import java.util.concurrent.TimeUnit;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/2 23:02
 *
 * * * * * * * * * * */
public class LockWho3 {
    public static void main(String[] args) throws InterruptedException {

        ENTRY3 entry = new ENTRY3();

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

class ENTRY3 {
    public synchronized void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);//sleep不放锁
        System.out.println("a");
    }

    public void b() {
        System.out.println("b");
    }
}
