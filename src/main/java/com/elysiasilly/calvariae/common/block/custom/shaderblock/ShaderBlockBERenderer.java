package com.elysiasilly.calvariae.common.block.custom.shaderblock;

import com.elysiasilly.calvariae.client.render.MyRenderTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class ShaderBlockBERenderer<T extends ShaderBlockEntity> implements BlockEntityRenderer<T> {

    public ShaderBlockBERenderer(BlockEntityRendererProvider.Context context) {
        super();
    }

    public void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {


        ShaderInstance shader = MyRenderTypes.testingInstance();

        shader.safeGetUniform("Colour").set(0, 255, 0);

        shader.setSampler("Test", Minecraft.getInstance().getMainRenderTarget().getDepthTextureId());

        Matrix4f matrix4f = poseStack.last().pose();
        VertexConsumer consumer = bufferSource.getBuffer(this.renderType());


        //Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.YELLOW_CONCRETE.defaultBlockState(), poseStack, bufferSource, packedLight, packedOverlay, ModelData.EMPTY, RenderType.solid());

        //Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getTexture()

        /*
        if (renderType() instanceof RenderType.CompositeRenderType compositeRenderType) {
            ShaderInstance myInstance = compositeRenderType.state.shaderState.shader;

            myInstance.safeGetUniform("Colour").set(0.0f, 1.0f, 0.0f);
        }

         */


        //RenderUtils.renderPlane(consumer, matrix4f, packedLight, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, false);


    }

    protected RenderType renderType() {
        return MyRenderTypes.testingShader();
    }
}
