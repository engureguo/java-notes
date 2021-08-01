package com.engure.lock;


import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
//         参数，是否公平
//         true if this lock should use a fair ordering policy，按照FIFO的规则从队列中取到自己
//         false或默认，a nonfair lock permits barging: threads requesting a lock can jump ahead of the queue of waiting threads
//                      if the lock happens to be available when it is requested. 上来直接占用锁，如果尝试失败，就再采用类似公平锁那种方式
//         非公平锁吞吐量更大，性能好，缺点是会产生饥饿
//         synchronized 是一种非公平锁
    }
}
