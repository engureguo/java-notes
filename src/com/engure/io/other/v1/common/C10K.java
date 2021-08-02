package com.engure.io.other.v1.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class C10K {

    public static void main(String[] args) {

        List<SocketChannel> list = new ArrayList<>();
        InetSocketAddress server  = new InetSocketAddress("127.0.0.1", 9999);
        long start = System.currentTimeMillis();
        try {

            for (int port = 10000; port < 25000; port++) {

                SocketChannel sc1 = SocketChannel.open();
                try {
                    sc1.bind(new InetSocketAddress("127.0.0.1", port));
                } catch (IOException e) {
                    String msg = e.getMessage();
                    System.out.println(msg);
                }
                sc1.connect(server);
                if (sc1.isOpen()) {
                    list.add(sc1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long end = System.currentTimeMillis();
            System.out.println("list size =" + list.size());
            System.out.println("time used =" + (end - start)+"ms");
        }

    }

}

/**
 *
 * win10，idea
 *
 * io.other.nio
 *  size = 15000, time = 124.7s
 *
 * io.other.bio
 *  size = 15000, time = 5.4s
 *
 * io.other.selector
 *  size = 15000, time = 122.5s
 *
 */
