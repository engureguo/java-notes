package com.engure.juc.other.v2.lock.lockwhat;

import java.util.concurrent.TimeUnit;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/2 23:02
 *
 * * * * * * * * * * */
public class LockWho0 {
    public static void main(String[] args) throws InterruptedException {

        ENTRY0 entry = new ENTRY0();

        new Thread(entry::a).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(entry::b).start();
    }
}

class ENTRY0 {
    public void a() {
        System.out.println("a");
    }

    public void b() {
        System.out.println("b");
    }
}
