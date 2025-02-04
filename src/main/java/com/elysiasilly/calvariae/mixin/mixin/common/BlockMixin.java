package com.elysiasilly.calvariae.mixin.mixin.common;

import com.elysiasilly.calvariae.core.registry.CParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SporeBlossomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Shadow public abstract BlockState defaultBlockState();

    @Shadow private BlockState defaultBlockState;

    @Inject(
            method = "animateTick",
            at = @At("HEAD")
    )

    private void calvariae$animateTick(BlockState state, Level level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if(state.is(Blocks.SCULK)) {
            ParticleUtils.spawnParticlesOnBlockFaces(level, pos, CParticles.WHITE_FLOWER_PARTICLE.get(), UniformInt.of(1, 1));
        }
    }

    @Inject(
            method = "createBlockStateDefinition",
            at = @At("HEAD")
    )

    private void calvariae$createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        if((Object) this instanceof SporeBlossomBlock) {
            builder.add(BlockStateProperties.FACING);
        }
    }

    @Inject(
            method = "getStateForPlacement",
            at = @At("HEAD"),
            cancellable = true
    )

    private void calvariae$getStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir) {
        if((Object) this instanceof SporeBlossomBlock) {
            BlockState state = defaultBlockState();

            System.out.println("SPORE BLOSSUM!!!!");

            state = state.setValue(BlockStateProperties.FACING, context.getClickedFace());

            cir.setReturnValue(state);
        }
    }

    @Inject(
            method = "registerDefaultState",
            at = @At("HEAD"),
            cancellable = true
    )

    private void calvariae$registerDefaultState(BlockState state, CallbackInfo ci) {
        if((Object) this instanceof SporeBlossomBlock) {
            this.defaultBlockState = state.setValue(BlockStateProperties.FACING, Direction.UP);

            ci.cancel();
        }
    }
}
