package com.engure.jvm.zy;

/**
 * <p>GC Roots 有哪些，根搜索算法</p>
 *
 * @author: engure
 * @created: 2021-08-30 上午 12:06
 **/
public class GCROOTDemo {

    //类静态属性引用的对象
    private static GCROOTDemo gcrootDemo2 = new GCROOTDemo();

    //常量引用的对象
    private static final GCROOTDemo gcrootDemo3 = new GCROOTDemo();

    private static void m1() {
        //虚拟机栈的本地方法表引用的对象
        GCROOTDemo gcrootDemo1 = new GCROOTDemo();
    }

    public static void main(String[] args) {
        m1();
    }

}
