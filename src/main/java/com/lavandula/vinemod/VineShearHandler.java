package com.lavandula.vinemod;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.registry.Registry;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;


public class VineShearHandler {

    // Register the event listener
    public static void register() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!world.isClient) { // Ensure this runs only on the server side
                ItemStack heldItem = player.getStackInHand(hand);
                BlockPos pos = hitResult.getBlockPos();
                BlockState blockState = world.getBlockState(pos);
                Block block = blockState.getBlock();

                // Check if the player is holding shears and interacting with CustomVineBlock
                if (heldItem.getItem() == Items.SHEARS && block instanceof CustomVineBlock) {
                    // Stop vine growth by toggling the "growing" block state
                    stopVineGrowth(world, pos);
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }

    // Define the behavior for stopping vine growth
    private static void stopVineGrowth(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);

        // Ensure the block is an instance of CustomVineBlock
        if (state.getBlock() instanceof CustomVineBlock) {
            // Set the "growing" state to false
            world.setBlockState(pos, state.with(CustomVineBlock.GROWING, false));

            // Optional: Add a console log for debugging
            System.out.println("Vine growth stopped at position: " + pos);

            // Play a sound effect for stopping growth
            world.playSound(
                    null,                     // No specific player (everyone nearby can hear it)
                    pos,                     // Position of the sound
                    SoundEvents.BLOCK_GROWING_PLANT_CROP, // The sound effect (default vine breaking sound)
                    SoundCategory.BLOCKS,    // Category for the sound (BLOCKS)
                    1.0f,                    // Volume
                    1.0f                     // Pitch
            );

            // Debug log for confirmation
            System.out.println("Vine growth stopped and sound played at: " + pos);
        }
    }
}
