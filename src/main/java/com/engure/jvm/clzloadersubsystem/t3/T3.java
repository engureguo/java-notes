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
        ClassLoader loader1 = Class.forName("java.lang.Thread").getClassLoader();//null
        System.out.println(loader1);

        // 当前线程上下文的classloader
        ClassLoader loader2 = Thread.currentThread().getContextClassLoader();//sun.misc.Launcher$AppClassLoader@14dad5dc
        System.out.println(loader2);

        // 获取系统的classloader
        ClassLoader loader3 = ClassLoader.getSystemClassLoader();//sun.misc.Launcher$AppClassLoader@14dad5dc
        System.out.println(loader3);
    }
}
