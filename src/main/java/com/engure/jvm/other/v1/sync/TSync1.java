package com.engure.jvm.other.v1.sync;

public class TSync1 {

    synchronized void m2() {

    }

    void m1() {
        synchronized (this) {

        }
    }

}
