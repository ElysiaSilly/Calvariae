package com.elysiasilly.calvariae.core;

import com.elysiasilly.calvariae.core.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@SuppressWarnings("unused")
@Mod(Calvariae.MODID)
public class Calvariae {

    public static final String MODID = "calvariae";

    public static ResourceLocation location(String string) { return ResourceLocation.fromNamespaceAndPath(MODID, string); }

    public static String prefix(String string) { return MODID + ":" + string; }

    public Calvariae(IEventBus bus, ModContainer modContainer) {

        CBlocks.BLOCKS.register(bus);
        CBlocks.BLOCKITEMS.register(bus);
        CCreativeTabs.TABS.register(bus);
        CBlockEntities.BLOCKENTITES.register(bus);
        CItems.ITEMS.register(bus);
        CEntities.ENTITIES.register(bus);
        CFeatures.FEATURES.register(bus);
        CParticles.PARTICLES.register(bus);
        CMenus.MENUS.register(bus);
        CComponents.COMPONENTS.register(bus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);

    }
}
