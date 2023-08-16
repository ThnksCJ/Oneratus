package com.thnkscj.client;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Main {
    @Mod.EventHandler
    public static void onPreInit(final FMLPreInitializationEvent event) {
        System.out.println("This is the PreInit event!");
    }

    @Mod.EventHandler
    public static void onInit(final FMLInitializationEvent event) {
        System.out.println("This is the Init event!");
    }

    @Mod.EventHandler
    public static void onPostInit(final FMLPostInitializationEvent event) {
        System.out.println("This is the PostInit event!");
    }
}
