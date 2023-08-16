package com.thnkscj.installer.util.library;

import com.thnkscj.installer.util.minecraft.MinecraftFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import static com.thnkscj.installer.Installer.jarPath;
import static com.thnkscj.installer.Installer.jarUrl;

public class LibraryInstaller {

    public static void download() throws Exception {
        File f = new File(MinecraftFiles.getLibraries() + jarPath);
        if (!f.exists()) {
            try {
                ReadableByteChannel rbc = Channels.newChannel(jarUrl.openStream());
                FileOutputStream fos = new FileOutputStream(MinecraftFiles.getLibraries() + jarPath);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                System.out.println("Successfully Downloaded Loader Jar");
            } catch (FileNotFoundException e) {
                System.out.println("The URL Did Not Find A Valid Jar To Download, Skipping");
            }
        } else {
            System.out.println("The Loader Jar Has Already Been Downloaded");
        }
    }

    public static URL toUrl(String s) {
        try {
            return new URL(s);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
