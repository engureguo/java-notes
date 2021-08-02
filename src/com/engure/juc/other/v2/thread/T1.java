package com.engure.juc.other.v2.thread;

/*******
 * Package: com.engure.juc.other.v2.thread   
 * Description: 
 * Author: engure
 * Date: 2021/8/2 16:55
 * Modified by: 
 ******/
public class T1 {
    public static void main(String[] args) {

        new Thread().start();//是java创建的线程吗？不是！

        System.out.println("CPU核心数" + Runtime.getRuntime().availableProcessors());

    }






}
