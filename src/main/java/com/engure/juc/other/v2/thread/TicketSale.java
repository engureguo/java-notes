package com.engure.juc.other.v2.thread;

/*******
 * Package: com.engure.juc.other.v2.thread   
 * Description: 窗口买票
 * Author: engure
 * Date: 2021/8/2 17:52
 * Modified by: 
 ******/
public class TicketSale {

    public static void main(String[] args) {

        SaleManager saleManager = new SaleManager();    // 资源

        // 多线程操作同一资源，耦合度较低，相对于以下写法
        //      1.new Thread(new R(xxx));
        //      2.class R implements Runnable {
        //           public void run() { }
        //        }

        new Thread(() -> {
            for (int i = 1; i < 30; i++) {
                saleManager.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i < 30; i++) {
                saleManager.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i < 30; i++) {
                saleManager.sale();
            }
        }, "C").start();
    }
}

class SaleManager {
    int num = 60;

    public synchronized void sale() {
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + " 买到了一张票，现在还剩" + (--num) + "张");
        }
    }
}