package com.cj.installer.util.minecraft;

import java.io.File;

public class MinecraftFiles
{
    private static String minecraft;
    private static String libraries;
    private static String versions;

    private static String os = System.getProperty("os.name").toLowerCase();

    public static String getMinecraft() {

        if (os.contains("nux")) {
            minecraft = System.getProperty("user.home") + "/.minecraft/";
        }
        else if (os.contains("darwin") || os.contains("mac")) {
            minecraft = System.getProperty("user.home") + "/Library/Application Support/minecraft/";
        }
        else if (os.contains("win")) {
            minecraft = System.getenv("APPDATA") + File.separator + ".minecraft" + File.separator;
        }

        return minecraft;
    }

    public static String getVersions() {
        getMinecraft();

        if (os.contains("nux")) {
            minecraft = System.getProperty("user.home") + "/.minecraft/";
        }
        else if (os.contains("darwin") || os.contains("mac")) {
            minecraft = System.getProperty("user.home") + "/Library/Application Support/minecraft/";
        }
        else if (os.contains("win")) {
            minecraft = System.getenv("APPDATA") + File.separator + ".minecraft" + File.separator;
        }

        if (minecraft != null) {
            versions  = minecraft + "versions"  + File.separator;
        }
        return versions;
    }

    public static String getLibraries() {
        getMinecraft();

        if (os.contains("nux")) {
            minecraft = System.getProperty("user.home") + "/.minecraft/";
        }
        else if (os.contains("darwin") || os.contains("mac")) {
            minecraft = System.getProperty("user.home") + "/Library/Application Support/minecraft/";
        }
        else if (os.contains("win")) {
            minecraft = System.getenv("APPDATA") + File.separator + ".minecraft" + File.separator;
        }

        if (minecraft != null) {
            libraries = minecraft + "libraries" + File.separator;
        }
        return libraries;
    }

}
