package org.example;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 8086;

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(hostname);

            Scanner userInput = new Scanner(System.in);
            while (true) {
                String message = userInput.nextLine();
                byte[] sendData = ("CHAT:Iago:" + message).getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);
                socket.send(sendPacket);
            }
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
