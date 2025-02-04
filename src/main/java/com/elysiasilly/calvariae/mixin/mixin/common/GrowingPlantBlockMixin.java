package com.elysiasilly.calvariae.mixin.mixin.common;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GrowingPlantBlock.class)
public class GrowingPlantBlockMixin {

    @ModifyReturnValue(
            method = "canSurvive",
            at = @At("RETURN")
    )

    private boolean calvariae$canSurvive(boolean original, @Local(ordinal = 1) BlockState state) {
        return (this.equals(Blocks.CAVE_VINES_PLANT) || this.equals(Blocks.CAVE_VINES)) && state.is(BlockTags.LEAVES) || original;
    }

}
