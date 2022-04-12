package com.engure.io.other.v1.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class ServerNIO {

    public static void main(String[] args) {
        List<SocketChannel> list = new ArrayList<>();

        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(9999));
            ssc.configureBlocking(false);

            while (true) {

                SocketChannel sock = ssc.accept(); // 非阻塞
                if (sock != null) {
                    sock.configureBlocking(false);
                    list.add(sock);
                    System.out.println("client:" + sock.getRemoteAddress().toString() +" connected!");
                }

                ByteBuffer buf = ByteBuffer.allocate(1024);
                for (SocketChannel soc :
                        list) {
                    buf.flip();
                    int c = soc.read(buf); // 非阻塞
                    if (c > 0) {
                        System.out.println("from " + soc.getRemoteAddress() + " :" + new String(buf.array(), c, 1024));
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
