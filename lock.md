
### 公平锁与配公平锁

**ReentrantLock** lock = new ReentrantLock(true);
- 参数fair，是否公平
    - true if this lock should use a fair ordering policy，按照FIFO的规则从队列中取到自己
    - false或默认，a nonfair lock permits barging: threads requesting a lock can jump ahead of the queue of waiting threads if the lock happens to be available when it is requested. 上来直接占用锁，如果尝试失败，就再采用类似公平锁那种方式
    - 非公平锁吞吐量更大，性能好，缺点是会产生饥饿
 **synchronized** 是一种非公平锁

 ### 可重入锁（也叫**递归锁**）

ReentrantLock、synchronized

指的是同一线程外层函数获得锁之后, 内层递归函数仍然能获取该锁的代码,在同一个线程在外层方法获取锁的时候, 在进入内层方法会自动获取锁

也即是说, **线程可以进入任何一个它已经拥有的锁所同步着的代码块。**

最大作用是 **避免死锁**。





