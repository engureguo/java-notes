package com.engure.juc.other.v1.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {

    public AtomicInteger atomicInteger;

    public void m(){
        for (int i = 0; i < 10000; i++) {
            atomicInteger.getAndIncrement();//Atomically increments by one the current value.
        }
    }

    public static void main(String[] args) {
        TestAtomicInteger test = new TestAtomicInteger();
        test.atomicInteger = new AtomicInteger(0);

        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new Thread(test::m));
        }

        list.forEach((thread) -> thread.start());

        list.forEach((thread) ->{
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });

        System.out.println(test.atomicInteger.get());
    }

}
