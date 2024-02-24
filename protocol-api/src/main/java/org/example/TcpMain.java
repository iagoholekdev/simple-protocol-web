package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpMain {
    public static void main(String[] args) {
        int port = 8086;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new ClientHandlerTcp(socket).start();
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}