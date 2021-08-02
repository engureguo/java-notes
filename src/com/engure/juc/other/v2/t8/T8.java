package com.engure.juc.other.v2.t8;

public class T8 {

    public static int x = 0;
    public static int y = 0;
    public static int a = 0;
    public static int b = 0;


    public static void main(String[] args) throws InterruptedException {

        for (int i=0; true; i++) {

            x = 0;
            y = 0;
            a = 0;
            b = 0;

            Thread t1 = new Thread(() -> {
                x = b;
                a = 1;
            });


            Thread t2 = new Thread(() -> {
                y = a;
                b = 2;
            });

            t2.start();
            t1.start();
            t1.join();
            t2.join();

            if (x == 2 && y == 1) {
                System.out.println("done " + i); // done 150879
                break;
            }

        }

    }

}
