package com.engure.jvm.clzloadersubsystem.t2;

import java.net.URL;
import java.net.URLClassLoader;

public class T22 extends URLClassLoader {
    public T22(URL[] urls) {
        super(urls);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
