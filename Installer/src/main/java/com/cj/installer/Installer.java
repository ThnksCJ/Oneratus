package com.cj.installer;

import com.cj.installer.ui.GUI;
import com.cj.shared.util.library.LibraryInstaller;
import com.cj.shared.util.version.VersionInstaller;

import java.io.File;

import static com.cj.shared.util.LogUtil.log;
import static com.cj.shared.util.minecraft.MinecraftFiles.getMinecraft;

public class Installer {

    public static void main(String[] args) throws Exception {
        log("Your Minecraft Directory Is at, '" + getMinecraft() + "'");
        File f = new File(getMinecraft() + "/username.oneratus");
        if(!f.exists()){
            GUI.main();
        }else{
            log("The File 'username.oneratus' Is Already Been Created.");
            log("If You Believe This Is A Mistake Then Please Remove It.");
            VersionInstaller.injectVersionJson();
            LibraryInstaller.dowload();
        }
    }
}
