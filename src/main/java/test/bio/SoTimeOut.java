package test.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class SoTimeOut {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert serverSocket != null;

        try {
            // 5 s 没有连接则 accept 抛异常 SocketTimeoutException
            serverSocket.setSoTimeout(5000);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        boolean exit = false;
        while (!exit) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                // 2s 读不完/写不完报错 SocketTimeoutException
                socket.setSoTimeout(2000);
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];

                while (true) {
                    System.out.println("等待数据");
                    int len = inputStream.read(bytes);
                    if (len == -1) {
                        break;
                    }

                    String s = new String(bytes);

                    if ("bye".equals(s.trim())) {
                        exit = true;
                        break;
                    }
                    System.out.println(s);
                }

            } catch (SocketTimeoutException e) {
                System.out.println("超时");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
