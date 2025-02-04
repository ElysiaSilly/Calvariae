package com.elysiasilly.calvariae.mixin.mixin.common;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import net.neoforged.neoforge.common.util.TriState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(IBlockExtension.class)
public interface IBlockExtensionMixin {

    @ModifyReturnValue(
            method = "canSustainPlant",
            at = @At("RETURN")
    )

    private TriState calvariae$canSustainPlant(TriState original) {
        return this.equals(Blocks.CLAY) ? TriState.TRUE : original;
    }

}