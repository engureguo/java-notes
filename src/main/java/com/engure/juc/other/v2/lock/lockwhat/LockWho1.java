package com.engure.juc.other.v2.lock.lockwhat;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/2 23:02
 *
 * * * * * * * * * * */
public class LockWho1 {
    public static void main(String[] args) throws InterruptedException {

        ENTRY1 entry1 = new ENTRY1();

        new Thread(()->{
            try {
                entry1.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(entry1::b).start();
    }
}

class ENTRY1 {
    public void a() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);//sleep不放锁
        System.out.println("a");
    }

    public void b() {
        System.out.println("b");
    }
}
