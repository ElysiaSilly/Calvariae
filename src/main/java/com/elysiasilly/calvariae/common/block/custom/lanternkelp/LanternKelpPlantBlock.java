package com.elysiasilly.calvariae.common.block.custom.lanternkelp;

import com.elysiasilly.calvariae.common.block.base.CMGrowingPlantBodyBlock;
import com.elysiasilly.calvariae.common.block.base.CMGrowingPlantHeadBlock;
import com.elysiasilly.calvariae.core.registry.CBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.Shapes;

import javax.annotation.Nullable;

public class LanternKelpPlantBlock extends CMGrowingPlantBodyBlock implements LiquidBlockContainer {

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource pRandom) {
        return 0;
    }

    public LanternKelpPlantBlock() {
        super(
                Properties.ofFullCopy(Blocks.KELP),
                Direction.UP, Shapes.block(), true);
    }

    @Override
    protected CMGrowingPlantHeadBlock getHeadBlock() {
        return (CMGrowingPlantHeadBlock) CBlocks.LANTERN_KELP_HEAD.get();
    }

    @Override
    protected boolean canGrowInto(BlockState pState) {
        return false;
    }

    @Override
    protected FluidState getFluidState(BlockState pState) {
        return Fluids.WATER.getSource(false);
    }

    @Override
    public boolean canAttachTo(BlockState pState) {
        return this.getHeadBlock().canAttachTo(pState);
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player pPlayer, BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }
}
