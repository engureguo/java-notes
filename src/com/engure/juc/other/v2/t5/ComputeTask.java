package com.engure.juc.other.v2.t5;

import java.util.concurrent.RecursiveTask;

public class ComputeTask extends RecursiveTask<Long> {

    Long start, end;
    Long threshold = 10_000L; // 阈值

    public ComputeTask(long s, long e) {
        start = s;
        end = e;
    }

    @Override
    protected Long compute() {
        if (end-start < threshold) {
            long sum = 0;
            for (long s=start; s<=end; s++) sum += s;
            return sum;
        } else {
            long mid = (end - start) / 2 + start;
            ComputeTask task1 = new ComputeTask(start, mid);
            ComputeTask task2 = new ComputeTask(mid + 1, end);
            task1.fork();
            task2.fork();
            return task1.join() + task2.join();
        }
    }

    public static void main(String[] args) {
        ComputeTask computeTask = new ComputeTask(1L, 10_0000_0000L);
        RunUtil.start();
        computeTask.fork();
        System.out.println(computeTask.join());
        RunUtil.endAndPrintRes();
    }

}
