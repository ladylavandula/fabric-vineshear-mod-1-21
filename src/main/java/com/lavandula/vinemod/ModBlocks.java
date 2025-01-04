package com.lavandula.vinemod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block CUSTOM_VINE_BLOCK = new CustomVineBlock(Block.Settings.copy(Blocks.VINE));

    // Register the block
    public static Block registerBlock(String name, Block block) {
        Identifier id = Identifier.of(VineMod.MOD_ID, name);

        // Register the block itself
        Registry.register(Registries.BLOCK, id, block);

        // Register the block item (for inventory visibility)
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        return block;
    }

    // Call this method in the initializer to load the block
    public static void initializeBlocks() {
        registerBlock("custom_vine_block", CUSTOM_VINE_BLOCK);
    }
}
