package com.engure.juc.other.v2.t7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T74 {
    public static void main(String[] args) {
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        System.out.println(list.getClass());
        System.out.println(new ArrayList<>().getClass());

//        AtomicInteger


    }
}
