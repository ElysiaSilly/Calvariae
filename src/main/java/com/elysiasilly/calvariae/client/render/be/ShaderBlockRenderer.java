package com.elysiasilly.calvariae.client.render.be;

import com.elysiasilly.calvariae.client.render.MyRenderTypes;
import com.elysiasilly.calvariae.core.Calvariae;
import com.elysiasilly.calvariae.core.registry.CBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import org.joml.Matrix4f;

@SuppressWarnings({"unused"})
@EventBusSubscriber(modid = Calvariae.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)

public class ShaderBlockRenderer {

    @SubscribeEvent
    public static void onRenderHandEvent(RenderHandEvent event) {


        if(!(event.getItemStack().is(CBlocks.SHADER_BLOCK.get().asItem()))) return;

        event.isCanceled();

        PoseStack pose = event.getPoseStack();

        //float f = Mth.sqrt(event.getSwingProgress());


        float a = 1.0F - event.getInterpolatedPitch() / 45.0F + 0.1F;
        a = Mth.clamp(a, 0.0F, 1.0F);
        float number =  -Mth.cos(a * (float) Math.PI) * 0.5F + 0.5F;

        //float f1 = -0.2F * Mth.sin(event.getSwingProgress() * (float) Math.PI);
        //float f2 = -0.4F * Mth.sin(f * (float) Math.PI);
        //pose.translate(0.0F, -f1 / 2.0F, f2);
        // ^ swing animation (breaking blocks and etc)

        pose.translate(0.0F, 0.04F * -1.2F + number * -0.5F, -0.72F);
        // ^ idk what this does either, when disabled its giant and renders at eye height

        //pose.mulPose(Axis.XP.rotationDegrees(number * -85.0F));
        // ^ slants it downwards when looking up

        //float f4 = Mth.sin(f * (float) Math.PI);
        //pose.mulPose(Axis.XP.rotationDegrees(f4 * 20.0F));
        // ^ swing animation
        //pose.scale(2.0F, 2.0F, 2.0F);

        //pose.mulPose(Axis.YP.rotationDegrees(180.0F));
        //pose.mulPose(Axis.ZP.rotationDegrees(180.0F));


        //pose.mulPose(Axis.XP.rotationDegrees(-interpolate / 4.0f));
        //pose.mulPose(Axis.ZP.rotationDegrees(interpolate / 4.0f));


        //pose.mulPose(Axis.YP.rotationDegrees(45));
        //pose.scale(1, 1, 1);
        //pose.translate(-0.5F, -0.5F, -0.5F);
        pose.scale(0.0078125F, 0.0078125F, 0.0078125F);

        float angleX = 0.1f * (Minecraft.getInstance().player.tickCount + event.getPartialTick()) * 90;

        pose.mulPose(Axis.YP.rotationDegrees(angleX));

        Matrix4f matrix4f = pose.last().pose();

        VertexConsumer consumer = event.getMultiBufferSource().getBuffer(MyRenderTypes.testingShader());

        //RenderUtils.renderCube(consumer, matrix4f, null, null, 50, event.getPackedLight(), true, false);


    }
}
