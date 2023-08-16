package com.thnkscj.client.mixins;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "init", at = @At("HEAD"))
    private void onPreInit(CallbackInfo ci) {
        System.out.println("Welcome");
    }

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 1, shift = At.Shift.AFTER))
    private void onInit(CallbackInfo ci) {
        System.out.println("Init");
    }

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 2, shift = At.Shift.AFTER))
    private void onPostInit(CallbackInfo ci) {
        System.out.println("PostInit");
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void onReady(CallbackInfo ci) {
        System.out.println("Ready");
    }
}
