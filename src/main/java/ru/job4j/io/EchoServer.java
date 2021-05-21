package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.startsWith("GET") && str.contains("Exit")) {
                            out.write("Server closed.\r\n".getBytes());
                            server.close();
                        } else if (str.startsWith("GET") && str.contains("Hello")) {
                            out.write("Hello, dear friend.\r\n".getBytes());
                        } else if (str.startsWith("GET")) {
                            out.write("What\r\n".getBytes());
                        }
                    }
                }
            }
        }
    }
}