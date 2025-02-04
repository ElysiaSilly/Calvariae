package com.elysiasilly.calvariae.client.render.entity;

import com.elysiasilly.calvariae.common.entity.LanternFlareEntity;
import com.elysiasilly.calvariae.core.Calvariae;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class LanternFlareRenderer extends EntityRenderer<LanternFlareEntity> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Calvariae.MODID, "textures/item/lantern_flare_popped.png");

    public LanternFlareRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(LanternFlareEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(LanternFlareEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        Vec3 directionTowardsCamera = this.entityRenderDispatcher.camera.getPosition().subtract(entity.getPosition(partialTick)).normalize();
        Vec3 dir = directionTowardsCamera.scale(8 / 16.0F);
        poseStack.translate(dir.x(), dir.y(), dir.z());

        float X = this.entityRenderDispatcher.camera.getXRot();
        float Y = this.entityRenderDispatcher.camera.getYRot();

        Matrix4f matrix4f = poseStack.last().pose();

        matrix4f.rotate(Axis.YP.rotationDegrees(-Y));
        matrix4f.rotate(Axis.XP.rotationDegrees(X - 90));

        //poseStack.mulPose(Axis.YP.rotationDegrees(-Y));
        //poseStack.mulPose(Axis.XP.rotationDegrees(X - 90));

        //poseStack.mulPose(Axis.ZP.rotationDegrees(20));

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutout(ResourceLocation.fromNamespaceAndPath(Calvariae.MODID, "textures/item/lantern_flare_popped.png")));

        //RenderUtil.drawPlane(consumer, poseStack.last().pose(), packedLight, 1, -.4f, 0, -.4f, .4f, 0, .4f, 0, 0, 0, false);

    }
}
