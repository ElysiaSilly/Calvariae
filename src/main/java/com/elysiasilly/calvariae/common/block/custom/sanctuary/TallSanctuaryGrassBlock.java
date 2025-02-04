package com.elysiasilly.calvariae.common.block.custom.sanctuary;

import com.elysiasilly.calvariae.common.block.base.TallGrassBlock;
import com.elysiasilly.calvariae.core.registry.CParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class TallSanctuaryGrassBlock extends TallGrassBlock {

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if(state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER) {
            super.animateTick(state, level, pos, random);
            if (random.nextInt(10) == 0) {
                ParticleUtils.spawnParticleBelow(level, pos, random, CParticles.SANCTUARY_MOSS_PARTICLE.get());
            }
        }
    }
}
