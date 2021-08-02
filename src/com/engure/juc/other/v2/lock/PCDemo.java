package com.engure.juc.other.v2.lock;

/*******
 * Description: 生产者消费者模式
 * Author: engure
 * Date: 2021/8/2 18:25
 ******/
public class PCDemo {

    public static void main(String[] args) {

        MyDataSet s = new MyDataSet();

        new Thread(() -> {
            try {
                for (int i = 1; i < 10; i++) {
                    s.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 1; i < 10; i++) {
                    s.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 1; i < 10; i++) {
                    s.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();

        new Thread(() -> {
            try {
                for (int i = 1; i < 10; i++) {
                    s.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();

    }
}

//生产者消费者：判断等待，业务，唤醒
class MyDataSet {

    private int num = 0;

    // 生产者
    public synchronized void increment() throws InterruptedException {
        while (num != 0) {
            //等待
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + " " + num);
        //唤醒
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (num == 0) {
            //等待
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + " " + num);
        //唤醒
        this.notifyAll();
    }

}



