package com.engure.juc.other.v1.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class TestFairLock {

    ReentrantLock lock = new ReentrantLock(true);//公平锁

    void m1(){
        while (true){
            try {
                lock.lock();

                System.out.println("m1....");

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }

    void m2(){

        while (true){
            try {
                lock.lock();

                System.out.println("m2....");

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }

    }

    public static void main(String[] args) {

        TestFairLock lock = new TestFairLock();

        Thread t1 = new Thread(lock::m1);
        Thread t2 = new Thread(lock::m2);

        t1.start();
        t2.start();


    }


}
