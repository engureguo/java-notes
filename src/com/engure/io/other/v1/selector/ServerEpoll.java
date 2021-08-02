package com.engure.io.other.v1.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServerEpoll {

    static Selector selector;


    public static void main(String[] args) {

        List<SocketChannel> clients = new ArrayList<>();

        try {
            selector = Selector.open();

            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(9999));
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {

                int num = selector.select();// ready channels num
                if (num > 0) {
                    Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey sk = it.next();
                        it.remove();

                        if (sk.isAcceptable()) {
                            ServerSocketChannel sc = ((ServerSocketChannel) sk.channel());
                            SocketChannel soc = sc.accept();
                            soc.configureBlocking(false);
                            soc.register(selector, SelectionKey.OP_READ);
                            clients.add(soc);
                            System.out.println("client:" + soc.getRemoteAddress().toString() +" connected!");
                        } else if (sk.isReadable()) {
                            SocketChannel sc = ((SocketChannel) sk.channel());
                            ByteBuffer buf = ByteBuffer.allocate(1024);
                            int c = sc.read(buf);
                            if (c > 0) {
                                System.out.println("read from" +sc.getRemoteAddress()+":"+
                                        new String(buf.array(), c, 1024));
                            }
                        }

                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
