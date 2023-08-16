package com.thnkscj.installer.util.minecraft;

import java.io.File;

public class MinecraftFiles {
    private static final String os = System.getProperty("os.name").toLowerCase();
    private static final String minecraft = getMinecraft();
    private static String libraries;
    private static String versions;

    public static String getMinecraft() {
        String minecraft = null;

        assert os != null;

        if (os.contains("nux")) {
            minecraft = System.getProperty("user.home") + "/.minecraft/";
        } else if (os.contains("darwin") || os.contains("mac")) {
            minecraft = System.getProperty("user.home") + "/Library/Application Support/minecraft/";
        } else if (os.contains("win")) {
            minecraft = System.getenv("APPDATA") + File.separator + ".minecraft" + File.separator;
        }

        return minecraft;
    }

    public static String getVersions() {
        if (minecraft != null) {
            versions = minecraft + "versions" + File.separator;
        }
        return versions;
    }

    public static String getLibraries() {
        getMinecraft();

        if (minecraft != null) {
            libraries = minecraft + "libraries" + File.separator;
        }
        return libraries;
    }
}
