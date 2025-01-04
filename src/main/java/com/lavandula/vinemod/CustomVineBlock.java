package com.lavandula.vinemod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.VineBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.server.world.ServerWorld;

import net.minecraft.util.math.random.Random;



public class CustomVineBlock extends VineBlock {
    public static final BooleanProperty GROWING = BooleanProperty.of("growing");

    public CustomVineBlock(Settings settings) {
        super(settings);
        // Set the default state with "growing" set to true
        this.setDefaultState(this.stateManager.getDefaultState().with(GROWING, true));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(GROWING); // Add the custom "growing" property
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Check if the vine is allowed to grow
        if (state.get(GROWING)) {
            // Allow the default vine growth behavior
            super.randomTick(state, world, pos, random);
        } else {
            // Do nothing if growing is false (effectively stops growth)
        }
    }
}
