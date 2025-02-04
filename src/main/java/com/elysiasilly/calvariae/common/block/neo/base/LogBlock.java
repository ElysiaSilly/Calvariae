package com.elysiasilly.calvariae.common.block.neo.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.Nullable;

public class LogBlock extends RotatedPillarBlock {

    final Block STRIPPED;
    final boolean FLAMMABLE;

    public LogBlock(@Nullable Block stripped, boolean flammable, Properties properties) {
        super(properties);
        this.STRIPPED = stripped;
        this.FLAMMABLE = flammable;
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        return context.getItemInHand().getItem() instanceof AxeItem && this.STRIPPED != null ? this.STRIPPED.withPropertiesOf(state) : null;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return this.FLAMMABLE;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return this.FLAMMABLE ? 20 : 0;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return this.FLAMMABLE ? 5 : 0;
    }
}
