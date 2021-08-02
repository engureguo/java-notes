package com.engure.io.other.v1.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBIO {

    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(9999);

            while (true) {
                Socket sock = ss.accept();
                System.out.println("client:" + sock.getRemoteSocketAddress().toString() +" connected!");
                new Thread(new Conn(sock)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class Conn implements Runnable {

    private final Socket socket;

    public Conn(Socket sock) {
        socket = sock;
    }

    @Override
    public void run() {
        try {
            int read = socket.getInputStream().read(); // 阻塞
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

