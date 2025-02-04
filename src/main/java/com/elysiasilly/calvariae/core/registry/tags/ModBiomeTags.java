package com.elysiasilly.calvariae.core.registry.tags;

import com.elysiasilly.calvariae.core.Util;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomeTags {

    public static final TagKey<Biome> HAS_THICK_FOG
            = Util.createBiomeTag("has_thick_fog");

    public static final TagKey<Biome> HAS_THICK_HAZE
            = Util.createBiomeTag("has_thick_haze");

}
