package com.engure.juc.other.v2.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/2 23:29
 *
 * * * * * * * * * * */
public class ListDemo {
    public static void main(String[] args) {

        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
        list.add("");
        list.get(1);

        CopyOnWriteArraySet<Object> set = new CopyOnWriteArraySet<>();
        set.add("");

        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put("","");//重要

    }
}
