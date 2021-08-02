package com.engure.jvm.clzloadersubsystem.t1;

public class T16 {
    public static void main(String[] args) {

        // 系统类加载器 或应用类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//sun.misc.Launcher$AppClassLoader@14dad5dc

        // 拓展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);//sun.misc.Launcher$ExtClassLoader@61bbe9ba

        // 引导类加载器
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);//null

        // 反推

        //该java程序的类加载器  -->  系统类加载器
        ClassLoader classLoader = T16.class.getClassLoader();
        System.out.println(classLoader);//sun.misc.Launcher$AppClassLoader@14dad5dc

        //String类加载其  -->  引导类加载器
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);//null

    }
}
