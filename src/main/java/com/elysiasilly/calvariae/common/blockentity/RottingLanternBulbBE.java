package com.elysiasilly.calvariae.common.blockentity;

import com.elysiasilly.babel.common.block.BabelBE;
import com.elysiasilly.babel.util.MCUtil;
import com.elysiasilly.calvariae.core.registry.CBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import static com.elysiasilly.calvariae.common.block.neo.special.RottingLanternBulbBlock.ACTIVE;

public class RottingLanternBulbBE extends BabelBE {

    public RottingLanternBulbBE(BlockPos pos, BlockState blockState) {
        super(CBlockEntities.ROTTING_LANTERN_BULB.get(), pos, blockState);
    }

    @Override
    public CompoundTag serialize(CompoundTag tag, HolderLookup.Provider registries) {
        return tag;
    }

    @Override
    public void deserialize(CompoundTag tag, HolderLookup.Provider registries) {

    }

    public void tick() {
        if(level == null) return;

        if(getBlockState().getValue(ACTIVE)) {
            if(level.getEntities(null, new AABB(getBlockPos()).inflate(.1)).isEmpty()) {

                level.destroyBlock(getBlockPos(), false);

                Vec3 vec3 = getBlockPos().getCenter();

                for (int i = 0; i < 20; i++) {

                    int min = -90, max = 180;
                    int X = level.getRandom().nextIntBetweenInclusive(min, max);
                    int Y = level.getRandom().nextIntBetweenInclusive(min, max);

                    MCUtil.particle.add(this.level, ParticleTypes.GLOW, vec3, new Vec3(0, 0, 0));

                    //LanternBulbSliceEntity projectile = new LanternBulbSliceEntity(level, vec3.x, vec3.y, vec3.z, 4);
                    //projectile.shootFromRotationNoShooter(X, Y, 0.0F, 1.0F, 1.0F);
                    //level.addFreshEntity(projectile);
                }
            }
        }
    }
}
