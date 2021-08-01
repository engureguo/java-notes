package com.engure.collection.list;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Demo1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    list.add(new Random(System.currentTimeMillis()).nextInt()); // java.util.ConcurrentModificationException
                    System.out.println(list);
                }
            }).start();
        }


    }

    // java.util.ConcurrentModificationException

    @Test
    public void test2() {
        List<Integer> list = new Vector<>(); // List接口

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    list.add(new Random(System.currentTimeMillis()).nextInt());
                    System.out.println(list);
                }
            }).start();
        }
    }

    @Test
    public void test3() {
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        List<Object> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
