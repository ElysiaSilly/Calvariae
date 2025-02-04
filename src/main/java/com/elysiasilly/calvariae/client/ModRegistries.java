package com.elysiasilly.calvariae.client;

import com.elysiasilly.calvariae.client.particle.RottingLanternGoopParticle;
import com.elysiasilly.calvariae.client.particle.SanctuaryMossParticle;
import com.elysiasilly.calvariae.client.particle.SweetwoodLeafParticle;
import com.elysiasilly.calvariae.client.particle.WhiteFlowerParticle;
import com.elysiasilly.calvariae.client.render.entity.LanternFlareRenderer;
import com.elysiasilly.calvariae.common.block.custom.shaderblock.ShaderBlockBERenderer;
import com.elysiasilly.calvariae.common.entity.temp.OlmModel;
import com.elysiasilly.calvariae.common.entity.temp.OlmRenderer;
import com.elysiasilly.calvariae.common.item.custom.LexiconCalvariae.LexiconEntriesDataMap;
import com.elysiasilly.calvariae.core.Calvariae;
import com.elysiasilly.calvariae.core.registry.CBlockEntities;
import com.elysiasilly.calvariae.core.registry.CEntities;
import com.elysiasilly.calvariae.core.registry.CParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterNamedRenderTypesEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@SuppressWarnings({"unused", "deprecation"})
@EventBusSubscriber(modid = Calvariae.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ModRegistries {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {

        // MISC ENTITY RENDERERS
        event.registerEntityRenderer(CEntities.LANTERN_BULB_SLICE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(CEntities.LANTERN_FLARE.get(), LanternFlareRenderer::new);

        // ENTITY RENDERERS
        event.registerEntityRenderer(CEntities.OLM_ENTITY.get(), OlmRenderer::new);

        // BLOCKENTITY RENDERES
        event.registerBlockEntityRenderer(CBlockEntities.SHADER_BLOCK_BE.get(), ShaderBlockBERenderer::new);
    }

    @SubscribeEvent
    private static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

        // LAYER DEFINITIONS
        event.registerLayerDefinition(OlmModel.LOCATION, OlmModel::createBodyLayer);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRegisterParticleProvider(RegisterParticleProvidersEvent event) {

        Minecraft.getInstance().particleEngine.register(CParticles.ROTTING_LANTERN_GOOP.get(), RottingLanternGoopParticle.Provider::new);

        Minecraft.getInstance().particleEngine.register(CParticles.SANCTUARY_MOSS_PARTICLE.get(), SanctuaryMossParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(CParticles.WHITE_FLOWER_PARTICLE.get(), WhiteFlowerParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(CParticles.SWEETWOOD_LEAF_PARTICLE.get(), SweetwoodLeafParticle.Factory::new);

    }

    public static void onRegisterNamedRenderTypes(RegisterNamedRenderTypesEvent event) {

        //event.register();

    }

    @SubscribeEvent
    public static void onRegisterMenuScreensEvent(RegisterMenuScreensEvent event) {

        //event.register(CMenus.LEXICON_SCREEN.get(), LexiconScreen::new);
    }

    @SubscribeEvent
    private static void onRegisterDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(LexiconEntriesDataMap.ENTRIES_DATA);
    }
}
