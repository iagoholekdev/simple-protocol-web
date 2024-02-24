package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandlerTcp extends Thread {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String name;
    private static Set<ClientHandlerTcp> clientHandlerTcps = ConcurrentHashMap.newKeySet();

    public ClientHandlerTcp(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            clientHandlerTcps.add(this);

            while (true) {
                String message = in.readLine();
                if (message == null) {
                    break;
                }

                String[] parts = message.split(":", 3);
                if (parts.length != 3) {
                    continue;
                }
                String messageType = parts[0];
                String sender = parts[1];
                String content = parts[2];
                System.out.println(Arrays.toString(parts));

                if (messageType.equals("CHAT")) {
                    name = sender;
                    broadcast(sender + ": " + content);
                }
            }
        } catch (IOException e) {
            System.out.println("ClientHandler error: " + e.getMessage());
        } finally {
            disconnect();
        }
    }

    private void broadcast(String message) {
        for (ClientHandlerTcp clientHandlerTcp : clientHandlerTcps) {
            if (clientHandlerTcp.name != null && !clientHandlerTcp.name.equals(name)) {
                try {
                    clientHandlerTcp.out.println(message);
                } catch (Exception e) {
                    System.out.println("Error broadcasting message: " + e.getMessage());
                }
            }
        }
    }

    private void disconnect() {
        if (name != null) {
            clientHandlerTcps.remove(this);
        }
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Socket close error: " + e.getMessage());
        }
    }
}
