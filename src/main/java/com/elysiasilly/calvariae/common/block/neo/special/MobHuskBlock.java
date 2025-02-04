package com.elysiasilly.calvariae.common.block.neo.special;

import com.elysiasilly.babel.common.block.BabelEntityBlock;
import com.elysiasilly.calvariae.common.blockentity.MobHuskBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MobHuskBlock extends BabelEntityBlock {

    public MobHuskBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MobHuskBE(blockPos, blockState);
    }
}
