package com.elysiasilly.calvariae.common.entity.temp;

import com.elysiasilly.calvariae.core.Calvariae;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OlmRenderer extends MobRenderer<OlmEntity, OlmModel<OlmEntity>> {

    private static final ResourceLocation OLM = ResourceLocation.fromNamespaceAndPath(Calvariae.MODID, "textures/entity/olm.png");

    public OlmRenderer(EntityRendererProvider.Context context) {
        super(context, new OlmModel<>(context.bakeLayer(OlmModel.LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(OlmEntity olmEntity) {
        return OLM;
    }
}
