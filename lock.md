
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

### 独占锁（写锁）/ 共享锁（写锁）/ 互斥锁

独占锁：指该锁一次只能被一个线程所持有。对 ReentrantLock 和 Synchronized 而言都是独占锁
共享锁：指该锁可被多个线程所持有。

对 ReentrantReadWriteLock 其读锁是共享锁, 其写锁是独占锁。
读锁的共享锁可保证并发读是非常高效的, **读写, 写读, 写写的过程是互斥的**。(涉及写的过程是互斥的，读读是可同时进行的)

案例（加锁版）：
before ---> after 由不加锁到加锁，加什么锁，实现了什么效果，有什么好处。分析前因后果
```java
class MyData {

    private volatile static Map<String, Object> map = new HashMap<>();
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    //不同于ReentrantLock：读写都互斥
    // ReentrantReadWriteLock：读写和写写互斥，读读不互斥

    public static void add(String key, Object value) {

        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始添加");

            map.put(key, value);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 添加结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public static void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始读取");
            Object res = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取结束：" + res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().lock();
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            int tmpI = i;
            new Thread(() -> {
                add(String.valueOf(tmpI), tmpI + 1);
            }, String.valueOf(i + 1)).start();
        }

        for (int i = 0; i < 5; i++) {
            int tmpI = i;
            new Thread(() -> {
                get(String.valueOf(tmpI));
            }, String.valueOf(i + 1)).start();
        }

        /*
            模拟多用户同时进行读写
             -写操作：原子+独占
                原子：写操作不可中断
                独占：别的线程不能读不能写
             -读操作：多个线程可同时读
         */

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
```

