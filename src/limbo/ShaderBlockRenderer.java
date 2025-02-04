package com.elysiasilly.calvariae.common.block.custom.shaderblock;

import com.elysiasilly.calvariae.client.render.MyRenderTypes;
import com.elysiasilly.calvariae.utils.RenderUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class ShaderBlockRenderer<T extends ShaderBlockEntity> implements BlockEntityRenderer<T> {

    ResourceLocation location = ResourceLocation.withDefaultNamespace("minecraft:dynamic/test_1");

    public ShaderBlockRenderer(BlockEntityRendererProvider.Context context) {
        super();
    }

    public void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        Matrix4f matrix4f = poseStack.last().pose();
        VertexConsumer consumer = bufferSource.getBuffer(this.renderType());

        RenderUtils.renderPlaneWithTextureDimensions(consumer, matrix4f, packedLight, 0, location, 0, 0.1f, 0, false);




        //float angleX = Mth.sin(0.1f * (Minecraft.getInstance().player.tickCount + partialTick)) * 90;
        //if(Minecraft.getInstance().player == null) return;
        float angleX = 0.1f * (Minecraft.getInstance().player.tickCount + partialTick) * 90;

        //poseStack.translate(0.5, 0.5, 0.5);

        poseStack.mulPose(Axis.YP.rotationDegrees(angleX));

        //if(blockEntity.getLevel() == null) return;
        //RenderUtils.renderCube(consumer, matrix4f, blockEntity.getBlockPos(), blockEntity.getLevel(), 1, packedLight, true, true);

        //DynamicTexture



        //Font font = Minecraft.getInstance().font;

        //font.drawInBatch(displayName, f1, (float)i, 553648127, false, matrix4f, bufferSource, flag ? Font.DisplayMode.SEE_THROUGH : Font.DisplayMode.NORMAL, j, packedLight);

        //poseStack.mulPose(Axis.XP.rotationDegrees(-180));
        //poseStack.translate(0, -5, 0);
        //poseStack.scale(0.5f, 0.5f, 0.5f);

        matrix4f = poseStack.last().pose();


        //float f1 = (float)(-font.width("▣▣") / 2);

        //font.drawInBatch("▣▣", f1, 0, 1, false, matrix4f, bufferSource, Font.DisplayMode.POLYGON_OFFSET, 1, packedLight);

    }

    protected RenderType renderType() {
        //return MyRenderTypes.testingShader();
        return RenderType.entitySolid(location);
    }
}
