package com.thnkscj.loader.tweaker;

import net.minecraftforge.fml.common.asm.transformers.AccessTransformer;

import java.io.IOException;

public final class CfgAccessTransformer extends AccessTransformer {
    public CfgAccessTransformer() throws IOException {
        super("client_at.cfg");
    }
}
