package com.engure.jvm.clzloadersubsystem.t3;

/*******
 * Package: com.engure.jvm.clzloadersubsystem.t3   
 * Description: 
 * Author: engure
 * Date: 2021/8/2 14:12
 * Modified by: 
 ******/
public class T3 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 某类的classloader
        ClassLoader loader1 = Class.forName("java.lang.Thread").getClassLoader();
        // 当前线程上下文的classloader
        ClassLoader loader2 = Thread.currentThread().getContextClassLoader();
        // 获取系统的classloader
        ClassLoader loader3 = ClassLoader.getSystemClassLoader();

    }
}
