package com.engure.jvm.other.v1.loader;
public class Paths {
    public static void main(String[] args) {

        String s1 = System.getProperty("sun.boot.class.path");
        System.out.println(s1.replaceAll(";", "\n"));

        System.out.println("-----------------------");
        String s2 = System.getProperty("java.class.path");
        System.out.println(s2.replaceAll(";", "\n"));

        System.out.println("-----------------------");
        String s3 = System.getProperty("java.ext.dirs");
        System.out.println(s3.replaceAll(";", "\n"));

    }
}

