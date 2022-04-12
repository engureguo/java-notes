package com.engure.jvm.zy;

/**
 * <p></p>
 *
 * @author: engure
 * @created: 2021-09-03 上午 01:06
 **/
public class DefaultSizeDemo {
    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();

        //约占物理总内存的64分之一
        System.out.println("totalMemory(-Xms 默认): " + totalMemory + "字节、" + (totalMemory) / (double) 1024 / 1024 + "兆");

        //约占物理总内存的4分之一（4.4？）
        System.out.println("maxMemory(-Xmx 默认): " + maxMemory + "字节、" + (maxMemory) / (double) 1024 / 1024 + "兆");
    }
}
