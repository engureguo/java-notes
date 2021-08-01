package com.engure.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo1 {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger();

        i.addAndGet(1);

    }
}

