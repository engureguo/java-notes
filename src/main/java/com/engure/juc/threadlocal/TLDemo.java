package com.engure.juc.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * <p>线程上下文</p>
 *
 * @author: engure
 * @created: 2021-09-02 上午 09:54
 **/
public class TLDemo {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            int finalI = i;
            new Thread(() -> {

                //放进threadlocalmap
                UserContext.set(new UserInfo(finalI + "", null, null));

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                }

                //从threadlocalmap中取
                UserInfo info1 = UserContext.get();
                System.out.println(Thread.currentThread().getName() + " " + info1);

            }, i + "").start();

        }

    }
}

