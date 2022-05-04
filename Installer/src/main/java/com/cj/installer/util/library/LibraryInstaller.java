package com.cj.installer.util.library;

import com.cj.installer.util.minecraft.MinecraftFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import static com.cj.installer.util.LogUtil.log;

public class LibraryInstaller {

    public static String jar_path  = "com/oneratus/injector/1.1.2/injector-1.1.2.jar";
    public static String jar_url = String.valueOf(toUrl("https://thnkscj.github.io/librarys/" + jar_path));

    public static void dowload() throws Exception {
        File f = new File(MinecraftFiles.getLibraries() + jar_path);
        if(!f.exists()){
            try {
                URL website = new URL(jar_url);
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream(MinecraftFiles.getLibraries() + jar_path);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                log("Successfully Downloaded Loader Jar");
            }catch(FileNotFoundException e){
                log("The URL Did Not Find A Valid Jar To Dowload, Skipping");
            }
        }else{
            log("The Loader Jar Has Already Been Dowloaded");
        }
    }

    private static URL toUrl(String s) {
        try {
            return new URL(s);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
