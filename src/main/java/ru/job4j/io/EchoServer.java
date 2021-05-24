package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    while (!str.isEmpty()) {
                        System.out.println(str);
                        if (str.startsWith("GET") && str.contains("Exit")) {
                            out.write("Server closed.\r\n".getBytes());
                            server.close();
                        } else if (str.startsWith("GET") && str.contains("Hello")) {
                            out.write("Hello, dear friend.\r\n".getBytes());
                        } else if (str.startsWith("GET")) {
                            out.write("What\r\n".getBytes());
                        }
                        str = in.readLine();
                    }
                } catch (IOException e) {
                    LOG.error("Output stream error", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Server socket error", e);
        }
    }
}