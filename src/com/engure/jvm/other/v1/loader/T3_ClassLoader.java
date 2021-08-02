package com.engure.jvm.other.v1.loader;

public class T3_ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> cls = T3_ClassLoader.class.getClassLoader().loadClass("com.engure.v1.loader.Paths");
        System.out.println(cls.getName());
    }
}
