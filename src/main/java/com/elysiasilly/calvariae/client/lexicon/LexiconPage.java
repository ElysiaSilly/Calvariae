package com.elysiasilly.calvariae.client.lexicon;

import com.elysiasilly.calvariae.client.lexicon.element.LexiconElement;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class LexiconPage {

    final ResourceLocation BACKGROUND;
    final List<LexiconElement> ELEMENTS = new ArrayList<>();

    public LexiconPage(ResourceLocation background, List<LexiconElement> elements) {
        this.BACKGROUND = background;
        this.ELEMENTS.addAll(elements);
    }

    public void render(PoseStack pose, MultiBufferSource source, float partialTicks) {
        for(LexiconElement element : this.ELEMENTS) {
            pose.pushPose();
            element.render(pose, source, partialTicks);
            pose.popPose();
        }
    }
}
