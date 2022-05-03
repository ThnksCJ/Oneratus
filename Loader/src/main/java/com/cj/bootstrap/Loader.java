package com.cj.bootstrap;

import com.cj.bootstrap.util.ByteLoader;
import com.cj.bootstrap.util.HWID;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Loader {
    public static void bootstrap() {
        try {
            ByteLoader loader = new ByteLoader();
            String fileURL = "http://localhost/api/1/client/jar/ThnksCJ?hwid=" + HWID.get().value;
            URL url = new URL(fileURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            InputStream inputStream = httpURLConnection.getInputStream();
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            ZipEntry zipEntry;

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String name = zipEntry.getName();
                if (!name.endsWith(".class"))
                    continue;
                name = name.substring(0, name.length() - 6);
                name = name.replace('/', '.');
                ByteArrayOutputStream streamBuilder = new ByteArrayOutputStream();
                byte[] tempBuffer = new byte[16384];
                int bytesRead;
                for (;
                     (bytesRead = zipInputStream.read(tempBuffer)) != -1; streamBuilder.write(tempBuffer, 0, bytesRead));
                loader.classes.put(name, streamBuilder.toByteArray());
            }

            final Class<?> mainClass = loader.loadClass("com.cj.client.Main");
            final Constructor<?> constructor = mainClass.getConstructor();

            final Object instance = constructor.newInstance();
            final Method startMethod = mainClass.getMethod("start");

            startMethod.invoke(instance);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
