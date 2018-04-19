package utils;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * 简单实现 HTTP 服务器
 */
public class HttpServer {
    private static final int port = 80;

    public static void service() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is running on port:" + serverSocket.getLocalPort());
        try {
            while (!Thread.interrupted()) {
                final Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress() + ":" + socket.getPort());
                doDispatch(socket);
            }
        } finally {
            serverSocket.close();
        }
    }

    private static void doDispatch(Socket socket) {
        new Thread(() -> {
            PrintStream out = null;
            try {
                String header = "HTTP/1.1 200 OK" + "\r\n" +
                        "Content-Type: text/html; charset=UTF-8" + "\r\n" +
                        "Connection: keep-alive" + "\r\n";

                out = new PrintStream(socket.getOutputStream());

                out.println(header);

                out.println("\r\n");
                out.println("<h1>Hello</h1>");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        try {
            service();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
