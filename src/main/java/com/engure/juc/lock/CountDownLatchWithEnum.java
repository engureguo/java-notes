package com.engure.juc.lock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/3 14:37
 *
 * * * * * * * * * * */
public class CountDownLatchWithEnum {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " arrived!");
                latch.countDown();
            }, MyEnum.ONE.foreach_get(i + 1).getDesp()).start();//NPE
        }

        latch.await();
        System.out.println(Thread.currentThread().getName() + " all arrived!");

    }

    @Test
    public void test() {
        MyEnum e = MyEnum.TWO.foreach_get(1);
        System.out.println(e.getDesp());
    }

}

/**
 * 一个枚举类 可理解为一张数据库表
 */
enum MyEnum {
    ONE(1, "A"),
    TWO(2, "B"),
    THREE(3, "C");

    private Integer id;
    private String desp;

    MyEnum(Integer i, String s) {
        this.id = i;
        this.desp = s;
    }

    public MyEnum foreach_get(int index) {
        for (MyEnum e : MyEnum.values()) {
            if (e.id == index) return e;
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public String getDesp() {
        return desp;
    }

}


