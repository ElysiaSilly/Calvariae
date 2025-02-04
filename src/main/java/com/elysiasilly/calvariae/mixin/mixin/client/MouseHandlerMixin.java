package com.elysiasilly.calvariae.mixin.mixin.client;

import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    /*

    @ModifyExpressionValue(
            method = "onPress",
            at = @At(
                    value = "INVOKE",
                    target = ""
            )
    )

    private boolean CaveMod$onPress(boolean original) {
        return original && LexiconRenderer.pos == 0;
    }

     */


}
