package com.engure.juc.other.v1.sync;

public class T1 {

    int a = 0;

    public synchronized void add() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class T2 extends Thread{
    T1 t1;
    public T2(T1 t){
        t1 = t;
    }

    @Override
    public void run() {
        t1.add();
        System.out.println("over:" + System.currentTimeMillis());
    }
}

class T3{
    public static void main(String[] args) {
        T1 t = new T1();
        new T2(t).start();
        new T2(t).start();
        new T2(t).start();
    }
}
