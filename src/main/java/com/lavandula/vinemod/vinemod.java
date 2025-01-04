package com.lavandula.vinemod;

import net.fabricmc.api.ModInitializer;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class vinemod implements ModInitializer {
    public static final String MOD_ID = "vinemod";
    public static final Block CUSTOM_VINE_BLOCK = new CustomVineBlock(Block.Settings.copy(Blocks.VINE));

    @Override
    public void onInitialize() {
        System.out.println("Vine Mod has been initialized!");


        Identifier vineId = Identifier.of(MOD_ID, "custom_vine_block");
        Registry.register(Registries.BLOCK, vineId, CUSTOM_VINE_BLOCK);

        // Register the event listener for shearing
        VineShearHandler.register();
    }
}



