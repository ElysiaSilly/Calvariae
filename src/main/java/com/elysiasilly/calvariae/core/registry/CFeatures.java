package com.elysiasilly.calvariae.core.registry;

import com.elysiasilly.calvariae.common.world.feature.*;
import com.elysiasilly.calvariae.core.Calvariae;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Calvariae.MODID);


    public static final DeferredHolder<Feature<?>, LushFossilFeature> BONETOWN_FEATURE
            = FEATURES.register("bonetown", LushFossilFeature::new);

    public static final DeferredHolder<Feature<?>, DripLeavesBasinFeature> DRIPLEAVES_BASIN_FEATURE
            = FEATURES.register("dripleaves_basin", DripLeavesBasinFeature::new);

    public static final DeferredHolder<Feature<?>, DebugFeature> DEBUG_FEATURE
            = FEATURES.register("debug", DebugFeature::new);

    public static final DeferredHolder<Feature<?>, RefactoredLushFossilFeature> REFACTORED_BONETOWN_FEATURE
            = FEATURES.register("refactored_bonetown", RefactoredLushFossilFeature::new);

    public static final DeferredHolder<Feature<?>, TestingFeature> TESTING_FEATURE
            = FEATURES.register("testing_feature", TestingFeature::new);
}


