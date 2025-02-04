package com.elysiasilly.calvariae.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class Util {

    /// ResourceKeys

    public static ResourceKey<ConfiguredFeature<?, ?>> createFeatureKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Calvariae.MODID, name));
    }

    /// TagKeys

    public static TagKey<Biome> createBiomeTag(String name) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Calvariae.MODID, name));
    }
    private static TagKey<Item> createItemTag(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Calvariae.MODID, name));
    }
    private static TagKey<Block> createBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Calvariae.MODID, name));
    }

    /// Registries

    public static BlockSetType blockSetType(String name) {
        return BlockSetType.register(new BlockSetType(Calvariae.prefix(name)));
    }

    public static WoodType woodType(String name, BlockSetType blockSet) {
        return WoodType.register(new WoodType(Calvariae.prefix(name), blockSet));
    }
}
