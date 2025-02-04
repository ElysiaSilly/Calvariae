package com.elysiasilly.calvariae.events;

import com.elysiasilly.calvariae.core.Calvariae;
import com.elysiasilly.calvariae.core.Config;
import com.elysiasilly.calvariae.core.registry.tags.ModBiomeTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import org.joml.Vector3f;

@SuppressWarnings({"unused", "ConstantConditions"})
@EventBusSubscriber(modid = Calvariae.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)

public class RenderFogEvent {

    public static Holder<Biome> biome;

    private static float hazeTransition = 0;
    private static float fogTransition = 0;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Pre event) {

        if(!Config.CUSTOM_FOG.get()) return;

        Entity player = Minecraft.getInstance().gameRenderer.getMainCamera().getEntity();

        if(player == null) return;
        Level level = player.level();
        BiomeManager biomemanager = level.getBiomeManager();
        Vec3 cameraInBiomeSpace = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().subtract(2.0D, 2.0D, 2.0D).scale(0.25D);
        Holder<Biome> biomeTemp = biomemanager.getNoiseBiomeAtQuart((int) cameraInBiomeSpace.x, (int) cameraInBiomeSpace.y, (int) cameraInBiomeSpace.z);

        if(biome == biomeTemp) return;
        biome = biomeTemp;
    }

    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {

        Minecraft.getInstance().level.effects().adjustLightmapColors(Minecraft.getInstance().level, (float) event.getPartialTick(), 1, 1, 0, 1, 1, new Vector3f(0, 0, 1));

        if(!Config.CUSTOM_FOG.get()) return;

        event.setCanceled(true);

        event.scaleNearPlaneDistance(0.15f);

        // TODO : time of day currently affects fog colour /:
        // TODO : apply thick fog when under y 64 ?

        if(biome == null) return;

        if(biome.is(ModBiomeTags.HAS_THICK_HAZE)) {
            hazeTransition = Mth.lerp(0.005f, hazeTransition, 1.0f);
        } else {
            hazeTransition = Mth.lerp(0.005f, hazeTransition, 0.0f);
        } if(hazeTransition > 0.01) {
            event.scaleNearPlaneDistance(Mth.lerp(hazeTransition, 1.0f, -2f));
        }

        if(biome.is(ModBiomeTags.HAS_THICK_FOG)) {
            fogTransition = Mth.lerp(0.005f, fogTransition, 1.0f);
        } else {
            fogTransition = Mth.lerp(0.005f, fogTransition, 0.0f);
        } if(fogTransition > 0.01) {
            event.scaleNearPlaneDistance(Mth.lerp(fogTransition, 1.0f, -0.25f));
        }
    }
}
