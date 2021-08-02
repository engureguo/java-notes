package com.engure.juc.other.v2.t3;

import java.util.Comparator;

public class Tt3 implements Comparator<Object> {

    public static void main(String[] args) {

        // new PriorityQueue<Integer>((o1, o2)->{return o1.compareTo(o2);});

        // Integer::compareTo;

    }

    // 实现 Comparator 接口
    @Override
    public int compare(Object o1, Object o2) {
        return 1;
    }
}
