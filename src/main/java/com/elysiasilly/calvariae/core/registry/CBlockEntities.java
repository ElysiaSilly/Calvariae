package com.elysiasilly.calvariae.core.registry;

import com.elysiasilly.calvariae.common.block.custom.shaderblock.ShaderBlockEntity;
import com.elysiasilly.calvariae.common.blockentity.MobHuskBE;
import com.elysiasilly.calvariae.common.blockentity.RottingLanternBulbBE;
import com.elysiasilly.calvariae.common.blockentity.StoneChestBE;
import com.elysiasilly.calvariae.core.Calvariae;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Calvariae.MODID);

    public static final Supplier<BlockEntityType<StoneChestBE>> STONE_CHEST_BE = BLOCKENTITES.register(
            "stone_chest_be", () -> BlockEntityType.Builder.of(StoneChestBE::new, CBlocks.STONE_CHEST.get()).build(null)
    );

    public static final Supplier<BlockEntityType<ShaderBlockEntity>> SHADER_BLOCK_BE = BLOCKENTITES.register(
            "shader_block_be", () -> BlockEntityType.Builder.of(ShaderBlockEntity::new, CBlocks.SHADER_BLOCK.get()).build(null)
    );

    public static final Supplier<BlockEntityType<RottingLanternBulbBE>> ROTTING_LANTERN_BULB = BLOCKENTITES.register(
            "rotting_lantern_bulb", () -> BlockEntityType.Builder.of(RottingLanternBulbBE::new, CBlocks.ROTTING_LANTERN_KELP_BULB.get()).build(null)
    );

    public static final Supplier<BlockEntityType<MobHuskBE>> MOB_HUSK = BLOCKENTITES.register(
            "mob_husk", () -> BlockEntityType.Builder.of(MobHuskBE::new, CBlocks.MOB_HUSK.get()).build(null)
    );
}
