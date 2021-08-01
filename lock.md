
### 公平锁与配公平锁

**ReentrantLock** lock = new ReentrantLock(true);
- 参数fair，是否公平
    - true if this lock should use a fair ordering policy，按照FIFO的规则从队列中取到自己
    - false或默认，a nonfair lock permits barging: threads requesting a lock can jump ahead of the queue of waiting threads if the lock happens to be available when it is requested. 上来直接占用锁，如果尝试失败，就再采用类似公平锁那种方式

非公平锁吞吐量更大，性能好 ⭐
 **synchronized** 是一种非公平锁

 ### 可重入锁（也叫**递归锁**）

ReentrantLock、synchronized

指的是同一线程外层函数获得锁之后, 内层递归函数仍然能获取该锁的代码,在同一个线程在外层方法获取锁的时候, 在进入内层方法会自动获取锁

也即是说, **线程可以进入任何一个它已经拥有的锁所同步着的代码块。**

最大作用是 **避免死锁**。

```java
public void test1() {

    synchronized (this) {
        //...
        synchronized (this) { // 进入内层方法会自动获得锁
            //....
        }
    }

}

public void test2() {
    ReentrantLock lock = new ReentrantLock();
    // 注意，lock和unlock的执行次数要保证相同！ 申请数 = 释放数

    lock.lock();
    try {

        lock.lock();
        try {

        } finally {
            lock.unlock();
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        lock.unlock();
    }

}
```

### 自旋锁 spinlock

是指尝试获取锁的线程不会立即阻塞, 而是采用循环的方式去尝试获取锁, 这样的好处是**减少线程上下文切换的消耗**, 缺点是循环会消耗CPU

Unsafe类
```java
public final int getAndAddInt(Object var1, long var2, int var4) {
    int var5;
    do {
        var5 = this.getIntVolatile(var1, var2);
    } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

    return var5;
}
```

实现一个简单的自旋锁：
```java
/**
 * 手写一个自旋锁
 */
public class MyCASLock {

    private static AtomicReference<Thread> atomicReference = new AtomicReference<>();//默认引用为null（Thread类型）

    public static void getLock() {
        Thread curThread = Thread.currentThread();
        System.out.println(curThread.getName() + " 尝试获取锁");
        while (!atomicReference.compareAndSet(null, curThread)) {

        }
        System.out.println(curThread.getName() + " 拿到了锁");
    }

    public static void releaseLock() {
        atomicReference.compareAndSet(Thread.currentThread(), null);
        System.out.println(Thread.currentThread().getName() + " 释放了锁");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {  //10个线程去抢占一把锁

            new Thread(() -> {
                getLock();

                // 模拟业务
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                releaseLock();
            }, "thread-" + i).start();

        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
```




