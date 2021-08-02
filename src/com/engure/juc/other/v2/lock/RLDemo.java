package com.engure.juc.other.v2.lock;

import java.util.concurrent.locks.ReentrantLock;

/*******
 * Package: com.engure.juc.other.v2.lock   
 * Description: 
 * Author: engure
 * Date: 2021/8/2 18:06
 * Modified by: 
 ******/
public class RLDemo {
    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        try {
            // 业务代码

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
