package com.cj.bootstrap;

import com.cj.shared.util.loader.PopUp;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static com.cj.shared.util.loader.MinecraftFiles.getMinecraft;

public class Injector implements ITweaker {

    public static final Logger log = LogManager.getLogger("Oneratus Loader");

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        Thread thread = new Thread(new InfoThread());
        thread.start();
    }

    @Override
    public String getLaunchTarget() {
        return null;
    }

    @Override
    public String[] getLaunchArguments() {
        return new String[0];
    }

    public class InfoThread implements Runnable {
        @Override
        public void run() {
            File f = new File(getMinecraft() + "/username.oneratus");
            if(f.exists()){
                try {
                    Scanner fileReader = null;
                    fileReader = new Scanner(f);
                    while (fileReader.hasNextLine()) {
                        String data = fileReader.nextLine();
                        if(runningFromIntelliJ()){
                            log.info("Running From Dev Workspace");
                            Loader.bootstrap(data);
                        }else {
                            AntiDump.check();
                            Loader.bootstrap(data);
                        }
                    }
                    fileReader.close();
                } catch (FileNotFoundException ignore) {
                }
            }else{
                PopUp.show("Error", "The File username.oneratus Cannot Be Found The Prossess Will Now Exit");
            }
        }
    }

    public static boolean runningFromIntelliJ() {
        String classPath = System.getProperty("java.class.path");
        return classPath.contains("idea_rt.jar");
    }
}
