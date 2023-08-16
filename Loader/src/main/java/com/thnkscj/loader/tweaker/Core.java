package com.thnkscj.loader.tweaker;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

@SuppressWarnings("unused")
public class Core {
    public void init(ClassLoader classLoader) {
        MixinBootstrap.init();

        MixinEnvironment.getEnvironment(MixinEnvironment.Phase.DEFAULT).setSide(MixinEnvironment.Side.CLIENT);
        MixinEnvironment.getEnvironment(MixinEnvironment.Phase.PREINIT).setSide(MixinEnvironment.Side.CLIENT);
        MixinEnvironment.getEnvironment(MixinEnvironment.Phase.INIT).setSide(MixinEnvironment.Side.CLIENT);
        MixinEnvironment.getEnvironment(MixinEnvironment.Phase.DEFAULT).setSide(MixinEnvironment.Side.CLIENT);

        Mixins.addConfiguration("mixins.client.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
    }

    public String[] getTransformers() {
        return new String[]{};
    }
}
