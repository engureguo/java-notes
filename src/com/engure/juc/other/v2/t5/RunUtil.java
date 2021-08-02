package com.engure.juc.other.v2.t5;

public class RunUtil {
    public static long start, end;
    public static void start() {
        start = System.currentTimeMillis();
    }
    public static void endAndPrintRes() {
        end = System.currentTimeMillis();
        System.out.println("time used = " + (end - start) + "ms");
    }
}
