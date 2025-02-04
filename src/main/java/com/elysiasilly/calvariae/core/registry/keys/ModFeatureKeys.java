package com.elysiasilly.calvariae.core.registry.keys;

import com.elysiasilly.calvariae.core.Util;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ModFeatureKeys {

    public static final ResourceKey<ConfiguredFeature<?, ?>> VELVET_MOSS_PATCH_BONEMEAL
            = Util.createFeatureKey("velvet/moss_patch_bonemeal");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SANCTUARY_MOSS_PATCH_BONEMEAL
            = Util.createFeatureKey("sanctuary/moss_patch_bonemeal");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BONETOWN
            = Util.createFeatureKey("bonetown");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DRIPLEAVES_BASIN
            = Util.createFeatureKey("dripleaves_basin");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DEBUG
            = Util.createFeatureKey("debug");

    public static final ResourceKey<ConfiguredFeature<?, ?>> REFACTORED_BONETOWN
            = Util.createFeatureKey("refactored_bonetown");
}
