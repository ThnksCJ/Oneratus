package com.thnkscj.server.socket;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class Socket {
    public static byte[] fileData;
    public static File file;

    public static void launch(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java Main <file>.jar");
            System.exit(1);
        }

        file = new File(args[0]);
        fileData = readFully(args[0]);

        ServerSocket socket = new ServerSocket(31212);

        System.out.println("Server started on port 31212");

        HWIDManager.flush();

        while (true) {
            java.net.Socket client = socket.accept();
            System.out.println("Client connected: " + client.getInetAddress().getHostAddress());

            new ClientHandler(client).start();
        }
    }

    public static byte[] readFully(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = fis.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        fis.close();
        return output.toByteArray();
    }
}