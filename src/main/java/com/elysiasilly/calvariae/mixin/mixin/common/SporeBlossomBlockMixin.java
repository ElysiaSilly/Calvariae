package com.elysiasilly.calvariae.mixin.mixin.common;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SporeBlossomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SporeBlossomBlock.class)
public abstract class SporeBlossomBlockMixin {

    @Shadow protected abstract boolean canSurvive(BlockState p_154709_, LevelReader p_154710_, BlockPos p_154711_);

    @Inject(
            method = "updateShape",
            at = @At("HEAD"),
            cancellable = true
    )

    private void calvariae$updateShape(BlockState state, Direction facing, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        //cir.setReturnValue(state.getValue(BlockStateProperties.FACING) == facing.getOpposite() && !this.canSurvive(state, level, neighborPos) ? Blocks.AIR.defaultBlockState() : state);
        cir.setReturnValue(state);
    }

    @Inject(
            method = "canSurvive",
            at = @At("HEAD"),
            cancellable = true
    )

    private void calvariae$canSurvive(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        //cir.setReturnValue(Block.canSupportCenter(level, pos, state.getValue(BlockStateProperties.FACING)) && !level.isWaterAt(pos));
        cir.setReturnValue(true);
    }
}
