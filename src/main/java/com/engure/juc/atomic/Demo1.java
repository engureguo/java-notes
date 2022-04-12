package com.engure.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class Demo1 {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger();

        i.addAndGet(1);

        Lock lock = null;

        System.out.println();
    }
}

