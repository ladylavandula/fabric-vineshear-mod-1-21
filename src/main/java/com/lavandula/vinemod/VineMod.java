package com.lavandula.vinemod;

import net.fabricmc.api.ModInitializer;

public class VineMod implements ModInitializer {
    public static final String MOD_ID = "vinemod";

    @Override
    public void onInitialize() {
        System.out.println("Vine Mod has been initialized!");

        // Call the block registration from the separate class
        ModBlocks.initializeBlocks();

        // Register the event listener for shearing
        VineShearHandler.register();
    }
}
