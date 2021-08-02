package com.engure.jvm.clzloadersubsystem.t1;

import sun.net.spi.nameservice.dns.DNSNameService;

import java.util.Arrays;
import java.util.List;

public class T1 {
    public static void main(String[] args) {
        System.out.println(List.class.getClassLoader());// null
        System.out.println(DNSNameService.class.getClassLoader()); // lib\ext\ --> ExtClassLoader
        System.out.println(T1.class.getClassLoader()); // classpath --> AppClassLoader

        Class clz = T1.class;
        System.out.println(clz.getName());
        System.out.println(clz.getSimpleName());
        System.out.println(clz.getClassLoader());
        System.out.println(clz);
        System.out.println(clz.getPackage());
        System.out.println(Arrays.toString(clz.getSigners()));
        System.out.println(clz.getTypeName());

    }
}
