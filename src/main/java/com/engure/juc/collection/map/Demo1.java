package com.engure.juc.collection.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Demo1 {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();

        Map<String, String> map2 = Collections.synchronizedMap(new HashMap<String, String>());

        Map<String, String> map3 = new ConcurrentHashMap<>();

        test(map);

    }

    public static void test(Map<String, String> map) {

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString().substring(0, 8), "PRESENT");  // ConcurrentModificationException ??
                System.out.println(map);
            }).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
