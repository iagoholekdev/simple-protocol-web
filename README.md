<!DOCTYPE html>
<html lang="en-US">

<head>
  <title>Simple Web Protocols - README</title>
</head>

<body>

# Simple Web Protocols - README

## Overview

This project demonstrates the implementation of two simple web protocols: UDP (User Datagram Protocol) and TCP (Transmission Control Protocol). The protocols are designed for basic communication between a client and a server in a chat-like application.

## TCP Protocol

### Concepts

TCP is a connection-oriented protocol that provides reliable and ordered communication between two devices. It ensures that data sent from one end is received in the same order on the other end and handles error recovery and flow control.

### How It Works

1. **Connection Establishment:**
    - The client initiates a connection to the server using a `Socket`.
    - A `ServerSocket` on the server side listens for incoming connections.
    - Upon connection, the server creates a new thread (`ClientHandler`) to handle communication with the client.

2. **Data Transfer:**
    - The client and server use `PrintWriter` and `BufferedReader` for sending and receiving messages.
    - The server continuously listens for incoming messages in a separate thread.
    - The client sends messages to the server with a predefined format (`"CHAT:Iago:message"`).

3. **Connection Termination:**
    - The connection is maintained until either the client or server decides to terminate it.

### Difference and Usage

- **Difference:**
    - TCP is connection-oriented, ensuring reliable and ordered delivery of data.
    - It establishes a connection before data transfer and terminates the connection when done.

- **Usage:**
    - TCP is suitable for applications where reliability and order of data are crucial, such as file transfer, email, and web browsing.

## UDP Protocol

### Concepts

UDP is a connectionless protocol that provides fast and simple communication. It does not guarantee reliable data delivery or order.

### How It Works

1. **Data Transfer:**
    - The client and server use `DatagramSocket` and `DatagramPacket` for sending and receiving messages.
    - The client sends messages to the server with a predefined format (`"CHAT:Iago:message"`).

2. **Connectionless:**
    - UDP does not establish a connection before sending data. Each message is independent.

### Difference and Usage

- **Difference:**
    - UDP is connectionless and does not guarantee reliable or ordered delivery of data.
    - It does not establish or terminate connections like TCP.

- **Usage:**
    - UDP is suitable for real-time applications like video streaming, online gaming, and VoIP, where low latency is essential, and occasional data loss is acceptable.

## Conclusion

Understanding the differences between TCP and UDP is crucial when designing network protocols. Choose TCP for reliable, ordered communication and UDP for scenarios where speed and simplicity are prioritized over reliability. The choice depends on the specific requirements of the application.

</body>

</html>
