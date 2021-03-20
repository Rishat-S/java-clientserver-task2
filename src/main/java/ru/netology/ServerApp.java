package ru.netology;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        final ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 23334));

        while (true) {
            try (SocketChannel socketChannel = serverChannel.accept()) {
                final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

                while (socketChannel.isConnected()) {
                    int byteCount = socketChannel.read(inputBuffer);
                    if (byteCount == -1) {
                        break;
                    }
                    String msg = new String(inputBuffer.array(), 0, byteCount,
                            StandardCharsets.UTF_8);
                    inputBuffer.clear();
                    System.out.println("Received message from client: " + msg);
                    msg = ExtraSpacesTrim.extraTrim(msg);
                    System.out.println("Formatted message: " + msg);
                    socketChannel.write(ByteBuffer.wrap(("Formatted message from the server: " + msg).getBytes(StandardCharsets.UTF_8)));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
