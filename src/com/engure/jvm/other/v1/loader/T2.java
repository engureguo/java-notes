package com.engure.jvm.other.v1.loader;

public class T2 {
    public static void main(String[] args) {
        System.out.println(T2.class.getClassLoader());//app
        System.out.println(T2.class.getClassLoader().getClass().getClassLoader());//null。appClassLoader的类加载器
        System.out.println(T2.class.getClassLoader().getParent());//app的父加载器是ext
        System.out.println(T2.class.getClassLoader().getParent().getParent());//ext的父加载器是null
    }
}
