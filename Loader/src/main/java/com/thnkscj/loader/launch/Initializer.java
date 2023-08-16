package com.thnkscj.loader.launch;

import com.thnkscj.client.Main;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "oneratus", name = "Oneratus", version = "1.0.0")
public final class Initializer {

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        Main.onPreInit(event);
    }

    @Mod.EventHandler
    public void onInit(final FMLInitializationEvent event) {
        Main.onInit(event);
    }

    @Mod.EventHandler
    public void onPostInit(final FMLPostInitializationEvent event) {
        Main.onPostInit(event);
    }
}
