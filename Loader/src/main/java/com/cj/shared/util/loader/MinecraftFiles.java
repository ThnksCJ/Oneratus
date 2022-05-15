package com.cj.shared.util.loader;

import java.io.File;

public class MinecraftFiles {

    private static String minecraft;
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

}
