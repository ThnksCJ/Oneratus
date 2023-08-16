package com.thnkscj.server.socket;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ClientHandler extends Thread {
    final java.net.Socket client;

    public ClientHandler(java.net.Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            DataInputStream dis = new DataInputStream(client.getInputStream());

            String hwid = dis.readUTF();

            System.out.println("Client HWID: " + hwid);

            if (HWIDManager.isHWIDValid(hwid)) {
                System.out.println("Client HWID Is Valid");

                byte[] bytes = new byte[(int) Socket.file.length()];

                FileInputStream fis = new FileInputStream(Socket.file);
                BufferedInputStream bis = new BufferedInputStream(fis);

                bis.read(bytes, 0, bytes.length);
                dos.write(bytes, 0, bytes.length);

                dos.flush();

                System.out.println("Sent File To Client");
            } else {
                client.close();
            }
        } catch (Exception e) {
            this.interrupt();
        }
    }
}
