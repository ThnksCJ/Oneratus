package com.thnkscj.loader;

import com.thnkscj.loader.launch.Loader;
import com.thnkscj.loader.tweaker.Core;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinTweaker;

import java.io.File;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@SuppressWarnings("unused")
public class Tweaker implements ITweaker {
    private final MixinTweaker wrapped;
    public static CountDownLatch latch;

    public Tweaker() {
        wrapped = new MixinTweaker();
    }

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        wrapped.acceptOptions(args, gameDir, assetsDir, profile);
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        try {
            latch = new CountDownLatch(1);
            Loader.initiate(classLoader);
            latch.await();
            wrapped.injectIntoClassLoader(classLoader);

            classLoader.addTransformerExclusion(Core.class.getName());
            Class<?> coreClass = Class.forName(Core.class.getName(), true, classLoader);
            Core core = (Core) coreClass.newInstance();
            core.init(classLoader);

            for (String transformer : core.getTransformers()) {
                classLoader.registerTransformer(transformer);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String getLaunchTarget() {
        return wrapped.getLaunchTarget();
    }

    @Override
    public String[] getLaunchArguments() {
        return wrapped.getLaunchArguments();
    }
}
