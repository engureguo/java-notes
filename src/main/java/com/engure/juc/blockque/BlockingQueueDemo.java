package com.engure.juc.blockque;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/* * * * * * * * * *
 * Description:
 * Author: engure
 * Date: 2021/8/3 13:58
 *
 * * * * * * * * * * */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.add("");
        blockingQueue.remove();
        blockingQueue.element();

        blockingQueue.offer("");
        blockingQueue.poll();
        blockingQueue.peek();

        blockingQueue.put("");
        blockingQueue.take();

        blockingQueue.offer("", 2, TimeUnit.SECONDS);
        blockingQueue.poll(2, TimeUnit.SECONDS);
    }
}
