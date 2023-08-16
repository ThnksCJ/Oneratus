package com.thnkscj.installer;

import com.thnkscj.installer.util.library.LibraryInstaller;
import com.thnkscj.installer.util.version.VersionInstaller;

import java.net.URL;

import static com.thnkscj.installer.util.library.LibraryInstaller.toUrl;

public class Installer {
    public static String mainClassPath = "com.thnkscj.loader.Tweaker";
    public static String jarPath = "com/thnkscj/loader/1.0.0/Loader-1.0.0.jar";
    public static URL jarUrl = toUrl("https://example.com/" + jarPath);

    public static void main(String[] args) throws Exception {
        LibraryInstaller.download();
        VersionInstaller.injectVersionJson();
    }
}
