package com.engure.juc.other.v2.t5;

import java.util.stream.LongStream;

public class ParallelAddDemo {
    public static void main(String[] args) {
        RunUtil.start();
        long sum = LongStream.rangeClosed(0L, 1_000_000_000L).sum();
        System.out.println(sum);
        RunUtil.endAndPrintRes();
    }
}
