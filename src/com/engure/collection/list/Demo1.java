package com.engure.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demo1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                for (int j=0; j<10; j++) {
                    list.add(new Random(System.currentTimeMillis()).nextInt()); // java.util.ConcurrentModificationException
                    System.out.println(list);
                }
            }).start();
        }

    }

    // java.util.ConcurrentModificationException



}
