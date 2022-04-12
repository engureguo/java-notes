package com.engure.jvm.zy;

/**
 * <p>
 *     虚拟机参数，XX参数重要！
 *     +PrintGCDetails
 *     +UseSerialGC
 *     MaxTenuringThreshold
 *     MetaspaceSize=1024m
 * </p>
 *
 * @author: engure
 * @created: 2021-08-30 上午 12:31
 **/
public class VMParamsXXDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello jvm~~~");
        Thread.sleep(Integer.MAX_VALUE);//查看虚拟机参数
    }
}