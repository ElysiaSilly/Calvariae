package com.elysiasilly.calvariae.mixin.mixin.client;

import com.elysiasilly.calvariae.mixin.IDontLikeNotBeingAbleToHotswap;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.LightTexture;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightTexture.class)
public class LightTextureMixin {

    @Inject(
            method = "updateLightTexture",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/DimensionSpecialEffects;adjustLightmapColors(Lnet/minecraft/client/multiplayer/ClientLevel;FFFFIILorg/joml/Vector3f;)V",
                    shift = At.Shift.AFTER
            )
    )

    public void test(float partialTicks, CallbackInfo ci, @Local(ordinal = 1) Vector3f vec) {
        IDontLikeNotBeingAbleToHotswap.deepDarkLightModification(vec);
    }
}
