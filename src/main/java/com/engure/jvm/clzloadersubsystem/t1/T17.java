package com.engure.jvm.clzloadersubsystem.t1;

import sun.misc.Launcher;

import java.net.URL;

public class T17 {
    public static void main(String[] args) {
        System.out.println("*******BootStrapClassLoader加载路径************");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }
        System.out.println("*******ExtClassLoader加载路径************");
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path:
             extDirs.split(";")) {
            System.out.println(path);
        }
    }
}
