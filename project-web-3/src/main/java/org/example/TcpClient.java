package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 8086;

        Scanner userInput = null;
        try (Socket socket = new Socket(hostname, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            new Thread(() -> {
                try {
                    while (true) {
                        String message = in.readLine();
                        if (message == null) {
                            break;
                        }
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Read error: " + e.getMessage());
                }
            }).start();

            userInput = new Scanner(System.in);
            while (true) {
                String message = userInput.nextLine();
                out.println("CHAT:Iago:" + message);
            }
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } finally {
            assert userInput != null;
            userInput.close();
        }
    }
}