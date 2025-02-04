package com.elysiasilly.calvariae.client.lexicon.element;

import com.elysiasilly.babel.util.RGBA;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class TextElement extends LexiconElement{

    public final List<FormattedCharSequence> TEXT = new ArrayList<>();

    public TextElement(Vec3 position, float rotation, Vec2 size, List<FormattedCharSequence> text) {
        super(position, rotation, size);
        this.TEXT.addAll(text);
    }


    @Override
    public void render(PoseStack pose, MultiBufferSource source, float partialTicks) {

        pose.translate(pos().x, pos().y, pos().z);
        pose.mulPose(Axis.ZP.rotationDegrees(rot()));

        for(int i = 0; i < this.TEXT.size(); i++) {

            Minecraft.getInstance().font.drawInBatch(
                    this.TEXT.get(i),
                    1f,
                    (5 * i),
                    RGBA.colours.BLACK.dec(),
                    false,
                    pose.last().pose(),
                    source,
                    Font.DisplayMode.NORMAL,
                    RGBA.colours.WHITE.dec(),
                    LightTexture.FULL_BRIGHT
            );

        }
    }
}
