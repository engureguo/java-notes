package com.engure.jvm.clzloadersubsystem.t3.java.lang;

/*******
 * Package: java.lang   
 * Description: 在 src/ 下测试
 * Author: engure
 * Date: 2021/8/2 14:55
 * Modified by: 
 ******/
public class Test {
    public static void main(String[] args) {
        String s = new String();//java.lang.SecurityException: Prohibited package name: java.lang，包名不被允许
    }
}
