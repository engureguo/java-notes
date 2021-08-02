package com.engure.jvm.clzloadersubsystem.t2;

import java.io.*;

public class T2 extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes;
        bytes = getByteByFileName(name);
        try {
            if (bytes == null) {
                throw new FileNotFoundException();
            } else {
                return defineClass(name, bytes, 0, bytes.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        throw new ClassNotFoundException();
    }

    private byte[] getByteByFileName(String name) {
        File file = new File(name);
        FileInputStream in = null;
        byte[] bytes = null;
        try {
            in = new FileInputStream(file);
            int len = in.available();
            bytes = new byte[len];
            in.read(bytes); // 加解密。。
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }
}
