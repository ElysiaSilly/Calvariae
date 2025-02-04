package com.elysiasilly.calvariae.common.block.custom;

import com.elysiasilly.calvariae.core.registry.CParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;

public class WhiteFlowerBlock extends TallFlowerBlock {
    public WhiteFlowerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if(state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER) {
            int ran = level.random.nextIntBetweenInclusive(1, 4);

            Vec3 offset = Vec3.ZERO;

            float val = .08f;

            switch(ran) {
                case 1 -> offset = new Vec3(-val, 0, val);
                case 2 -> offset = new Vec3(val, 0, -val);
                case 3 -> offset = new Vec3(-val, 0, -val);
                case 4 -> offset = new Vec3(val, 0, val);
            }

            Vec3 vec = pos.getCenter().add(state.getOffset(level, pos)).add(0, .55, 0).add(offset);


            level.addParticle(CParticles.WHITE_FLOWER_PARTICLE.get(), vec.x, vec.y, vec.z, 1, 1, 1);
        }
    }

    @Override
    protected void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState state) {
        super.spawnDestroyParticles(level, player, pos, state);
    }
}
