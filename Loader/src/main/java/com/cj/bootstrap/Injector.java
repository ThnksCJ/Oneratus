package com.cj.bootstrap;

import com.cj.bootstrap.util.ThreadUtil;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.util.List;

import static com.cj.bootstrap.util.MinecraftFiles.getMinecraft;

public class Injector implements ITweaker {

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        File f = new File(getMinecraft() + "/username.oneratus");

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
                Loader.bootstrap();
            }
        }
    }
}
