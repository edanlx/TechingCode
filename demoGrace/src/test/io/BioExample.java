package io;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioExample {
    @Test
    public void StartMultiThread() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            // telnet 127.0.0.1 9000
            Socket clientSocket = serverSocket.accept();
//            handler(clientSocket);
            new Thread(() -> handler(clientSocket)).start();
        }
    }

    @SneakyThrows
    private static void handler(Socket clientSocket) {
        byte[] bytes = new byte[1024];
        System.out.println("准备read。。");
        //接收客户端的数据，阻塞方法，没有数据可读时就阻塞
        int read = clientSocket.getInputStream().read(bytes);
        System.out.println("read完毕。。");
        if (read != -1) {
            System.out.println("接收到客户端的数据：" + new String(bytes, 0, read));
        }
        clientSocket.getOutputStream().
                write("HelloClient".getBytes());
        clientSocket.getOutputStream().
                flush();
    }
}
