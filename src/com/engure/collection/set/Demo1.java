package com.engure.collection.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Demo1 {
    public static void main(String[] args) {

        Set<String> s0 = new HashSet<>();
        //1
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        //2
        Set<String> s = new CopyOnWriteArraySet<>();

        test(s0);

    }


    public static void test(Set<String> set) {

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));  // ConcurrentModificationException ??
                System.out.println(set);
            }).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
