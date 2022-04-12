package com.engure.juc.other.v1.sem;

import java.util.concurrent.Semaphore;

public class TestSemaphore {


    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        try {
            sem.acquire();
            sem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
