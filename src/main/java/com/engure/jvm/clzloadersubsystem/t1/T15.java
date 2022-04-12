package com.engure.jvm.clzloadersubsystem.t1;

class T {

    static {
        if (true) {
            System.out.println(Thread.currentThread().getName()+" initializing start!");
//            while (true) {
//
//            }
        }
    }

}

public class T15 {
    public static void main(String[] args) {
        for (int i=0; i<20; i++) {

            new Thread(()->{
                System.out.println(Thread.currentThread().getName() +" begin");
                T t = new T();
                System.out.println(Thread.currentThread().getName() +" end");
            }).start();

        }
    }
}
