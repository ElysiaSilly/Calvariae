package com.elysiasilly.calvariae.common.block.base;

import com.elysiasilly.babel.common.block.api.ITransformOnBurnBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BaseTransformOnBurnBlock extends Block implements ITransformOnBurnBlock {

    public BaseTransformOnBurnBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
    }

    @Override
    public void onBurn(Level level, BlockPos pos, Direction face, BlockState state) {
        level.setBlockAndUpdate(pos, Blocks.ACACIA_PLANKS.defaultBlockState());
    }
}
