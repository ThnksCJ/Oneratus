package com.thnkscj.loader.launch;

import com.thnkscj.loader.util.HWID;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.*;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.thnkscj.loader.Tweaker.latch;
public class Loader {
    public static void initiate(LaunchClassLoader classLoader) {
        try {
            Socket socket = new Socket("127.0.0.1", 31212);
            DataInputStream inputF = new DataInputStream(socket.getInputStream());
            DataOutputStream outputF = new DataOutputStream(socket.getOutputStream());

            outputF.writeUTF(HWID.generate());

            Field field = LaunchClassLoader.class.getDeclaredField("resourceCache");
            field.setAccessible(true);

            @SuppressWarnings("unchecked")
            Map<String, byte[]> classCache = (Map<String, byte[]>) field.get(Launch.classLoader);
            Map<String, byte[]> resources = new HashMap<>();

            try (ZipInputStream zipStream = new ZipInputStream(inputF)) {
                ZipEntry zipEntry;
                while ((zipEntry = zipStream.getNextEntry()) != null) {
                    byte[] buffer = new byte[1024];

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int len;
                    while ((len = zipStream.read(buffer)) > 0) {
                        bos.write(buffer, 0, len);
                    }
                    bos.close();

                    if (zipEntry.getName().endsWith(".class")) {
                        classCache.put(zipEntry.getName().replace(".class", "").replace('/', '.'), bos.toByteArray());
                    } else {
                        resources.put(zipEntry.getName(), bos.toByteArray());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!resources.isEmpty()) {
                try {
                    File tempFile = File.createTempFile("resources", ".jar");
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    JarOutputStream jos = new JarOutputStream(fos);
                    for (Map.Entry<String, byte[]> entry : resources.entrySet()) {
                        jos.putNextEntry(new ZipEntry(entry.getKey()));
                        jos.write(entry.getValue());
                        jos.closeEntry();
                    }

                    jos.close();
                    fos.close();
                    tempFile.deleteOnExit();

                    classLoader.addURL(tempFile.toURI().toURL());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            latch.countDown();
        } catch (Exception ignored) {
        }
    }
}
