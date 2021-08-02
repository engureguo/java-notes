package com.engure.jvm.clzloadersubsystem.t1;

class A {
    static {
        System.out.println("a");
    }
}

class B extends A {
    static {
        System.out.println("b");
    }
}

public class T14 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.engure.jvm.clzloadersubsystem.t1.B");
    }
}
