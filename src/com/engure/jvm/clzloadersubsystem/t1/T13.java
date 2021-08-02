package com.engure.jvm.clzloadersubsystem.t1;

public class T13 {
    private static int a = 2;

    static {
        a = 10;
        b = 20;// 可赋值，不可使用
        System.out.println(a);
        // System.out.println(b);//报错，非法前向引用
    }

    private static int b = 10;

    public static void main(String[] args) {
        System.out.println(a); // 10
        System.out.println(b); // 10

        // prepare: static变量赋值为 0
        // initial: static变量赋值和静态代码块 按照先后循序合并从上到下执行

    }
}
