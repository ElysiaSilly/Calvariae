package com.elysiasilly.calvariae.client.lexicon.element;

import com.elysiasilly.babel.client.screen.BabelScreenUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class ImageElement extends LexiconElement{

    private final ResourceLocation PATH;

    public ImageElement(Vec3 position, float rotation, Vec2 size, ResourceLocation path) {
        super(position, rotation, size);
        this.PATH = path;
    }

    @Override
    public void render(PoseStack pose, MultiBufferSource source, float partialTicks) {

    }
}
