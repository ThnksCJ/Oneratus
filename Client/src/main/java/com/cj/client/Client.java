package com.cj.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(
        modid = Client.MOD_ID,
        name = Client.MOD_NAME,
        version = Client.VERSION
)
public class Client {

    public static final String MOD_ID = "Client";
    public static final String MOD_NAME = "Client";
    public static final String VERSION = "1.0-SNAPSHOT";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // if we can find the bootstrap then loading the client is not necessary thru forge
        try {
            getClass().getClassLoader().loadClass("com.cj.bootstrap.Loader");
            return;
        } catch (ClassNotFoundException ignored) {}
        // looks like we are debugging - lets load the client!
        Loading loading = new Loading();
        loading.load();
    }

}
