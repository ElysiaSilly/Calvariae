package com.elysiasilly.calvariae.core.registry;

import com.elysiasilly.calvariae.core.Calvariae;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, Calvariae.MODID);

    public static final Supplier<SimpleParticleType> SANCTUARY_MOSS_PARTICLE = PARTICLES.register("sanctuary_moss_particle",
            () -> new SimpleParticleType(true));

    public static final Supplier<SimpleParticleType> WHITE_FLOWER_PARTICLE = PARTICLES.register("white_flower_particle",
            () -> new SimpleParticleType(true));

    public static final Supplier<SimpleParticleType> SWEETWOOD_LEAF_PARTICLE = PARTICLES.register("sweetwood_leaf_particle",
            () -> new SimpleParticleType(true));

    public static final Supplier<SimpleParticleType> ROTTING_LANTERN_GOOP = PARTICLES.register("rotting_lantern_goop",
            () -> new SimpleParticleType(true));
}
