package com.elysiasilly.calvariae.client.screen.lexicon;

import com.elysiasilly.babel.util.RGBA;
import com.elysiasilly.babel.util.RenderUtil;
import com.elysiasilly.calvariae.client.lexicon.LexiconResources;
import com.elysiasilly.calvariae.common.item.custom.LexiconCalvariae.LexiconEntriesDataMap;
import com.elysiasilly.calvariae.common.item.neo.LexiconItem;
import com.elysiasilly.calvariae.core.Calvariae;
import com.elysiasilly.calvariae.core.limbo.Texture;
import com.google.common.base.Predicates;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import org.joml.Matrix4f;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@EventBusSubscriber(modid = Calvariae.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class LexiconRenderer {

    public static final Minecraft minecraft = Minecraft.getInstance();

    private static ResourceManager resourceManager;
    private static TextureManager textureManager;

    public static Texture texture;

    static PoseStack stack;
    static MultiBufferSource bufferSource;
    static RenderHandEvent event;
    static Font font;
    static int packedLight;
    static Matrix4f matrix4f;
    static MouseHandler mouseHandler;

    public static Set<LexiconEntriesDataMap.LexiconEntries> entries = new HashSet<>();
    public static int previousPage;
    public static int currentPage;
    public static boolean flipForwards;
    public static boolean flipBackwards;
    public static float startTime;

    public static ItemStack carriedItem = ItemStack.EMPTY;

    public static float pos = 1;

    public static final ResourceLocation COVER = Calvariae.location("textures/lexicon/cover.png");

    @SubscribeEvent
    public static void onRenderHandEvent(RenderHandEvent event) {
        if(event.getItemStack().getItem() instanceof LexiconItem) {
            event.setCanceled(true);

            PoseStack pose = event.getPoseStack();
            MultiBufferSource source = event.getMultiBufferSource();

            pose.pushPose();

            pose.translate(-.5, -.5, -1);
            pose.mulPose(Axis.YP.rotationDegrees(90));

            //pose.mulPose(Axis.YP.rotationDegrees(10));

            pose.pushPose();



            Map<ResourceLocation, Resource> resources = Minecraft.getInstance().getResourceManager().listResources("lexicon", Predicates.alwaysTrue());

            //for(Map.Entry<ResourceLocation, Resource> entry : resources.entrySet()) {
            //    System.out.println(entry.getKey());
            //}

            ResourceLocation location = ResourceLocation.fromNamespaceAndPath("lexicon", "textures/block/custom_texture.png");

            ResourceLocation page = Calvariae.location("textures/lexicon/page.png");



            if(Minecraft.getInstance().screen instanceof LexiconScreen screen) {
                pose.pushPose();

                pose.translate(-.001, 0, -.3750);

                RenderSystem.enableScissor(0, 0, 60, 60);


                drawPlane(
                        source.getBuffer(RenderType.entityCutout(location)),
                        pose.last().pose(),
                        event.getPackedLight(),
                        RGBA.colours.WHITE,
                        Vec3.ZERO,
                        new Vec3(0, 1, 3 / 4d),
                        Vec2.ZERO,
                        new Vec2((1 / 128f) * 48, (1 / 128f) * 64)
                );

                RenderSystem.disableScissor();

                pose.popPose();

            }

            pose.translate(0, 0, -.3750);

            pose.pushPose();

            drawPlane(
                    source.getBuffer(RenderType.entityCutout(page)),
                    pose.last().pose(),
                    event.getPackedLight(),
                    RGBA.colours.WHITE,
                    Vec3.ZERO,
                    new Vec3(0, 1, 3 / 4d),
                    Vec2.ZERO,
                    new Vec2((1 / 128f) * 48, (1 / 128f) * 64)
            );

            pose.translate(0, 0, 3 / 4d);

            drawPlane(
                    source.getBuffer(RenderType.entityCutout(page)),
                    pose.last().pose(),
                    event.getPackedLight(),
                    RGBA.colours.WHITE,
                    Vec3.ZERO,
                    new Vec3(0, 1, 1 / 4d),

                    new Vec2((1 / 128f) * 48, 0),
                    new Vec2((1 / 128f) * 64, (1 / 128f) * 64)
            );

            pose.popPose();

            pose.translate(0, 0, 1);

            drawPlane(
                    source.getBuffer(RenderType.entityCutout(page)),
                    pose.last().pose(),
                    event.getPackedLight(),
                    RGBA.colours.WHITE,
                    Vec3.ZERO,
                    new Vec3(0, 1, 3 / 3d),

                    new Vec2((1 / 128f) * 64, 0),
                    new Vec2((1 / 128f) * 128, (1 / 128f) * 64)
            );

            pose.popPose();
        }
    }


    public static void drawPlane(VertexConsumer consumer, Matrix4f matrix4f, int packedLight, RGBA rgba, Vec3 start, Vec3 end, Vec2 startUV, Vec2 endUV) {
        drawPlane(consumer, matrix4f, packedLight, rgba, start, end, new Vec3(end.x, end.y, start.z), new Vec3(start.x, start.y, end.z), startUV, endUV);
    }

    private static void drawPlane(VertexConsumer consumer, Matrix4f matrix4f, int packedLight, RGBA rgba, Vec3 a, Vec3 b, Vec3 c, Vec3 d, Vec2 startUV, Vec2 endUV) {

        consumer.addVertex(matrix4f, (float) c.x, (float) c.y, (float) c.z)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv(startUV.x, startUV.y)
                .setLight(packedLight)
                .setColor(rgba.red, rgba.green, rgba.blue, rgba.alpha)
                .setUv1(0, 0) // ?
                .setUv2(1, 1) // ?
                .setNormal(0, 1, 0); // ?

        consumer.addVertex(matrix4f, (float) a.x, (float) a.y, (float) a.z)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv(startUV.x, endUV.y)
                .setLight(packedLight)
                .setColor(rgba.red, rgba.green, rgba.blue, rgba.alpha)
                .setUv1(0, 0)
                .setUv2(1, 1)
                .setNormal(0, 1, 0);

        consumer.addVertex(matrix4f, (float) d.x, (float) d.y, (float) d.z)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv(endUV.x, endUV.y)
                .setLight(packedLight)
                .setColor(rgba.red, rgba.green, rgba.blue, rgba.alpha)
                .setUv1(0, 0)
                .setUv2(1, 1)
                .setNormal(0, 1, 0);

        consumer.addVertex(matrix4f, (float) b.x,   (float) b.y,   (float) b.z)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv(endUV.x, startUV.y)
                .setLight(packedLight)
                .setColor(rgba.red, rgba.green, rgba.blue, rgba.alpha)
                .setUv1(0, 0)
                .setUv2(0, 0)
                .setNormal(0, 1, 0);
    }


}
