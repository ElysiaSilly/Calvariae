package com.elysiasilly.calvariae.common.blockentity;

import com.elysiasilly.babel.common.block.BabelBE;
import com.elysiasilly.calvariae.core.registry.CBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MobHuskBE extends BabelBE {

    Entity entity;

    public MobHuskBE(BlockPos pos, BlockState blockState) {
        super(CBlockEntities.MOB_HUSK.get(), pos, blockState);
    }

    public void tick() {
        EntityType<?> type = entity.getType();
    }

    @Override
    public CompoundTag serialize(CompoundTag tag, HolderLookup.Provider registries) {
        this.entity.save(tag);
        return tag;
    }

    @Override
    public void deserialize(CompoundTag tag, HolderLookup.Provider registries) {
        //this.entity = EntityCodSalmonFix
    }
}
