package com.elysiasilly.calvariae.common.block.neo.special;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LanternBulbBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(1.0, 1.0, 1.0, 15.0, 15.0, 15.0);

    public static final BooleanProperty ACTIVE = RottingLanternBulbBlock.ACTIVE;

    public LanternBulbBlock() {
        super(Properties.ofFullCopy(Blocks.MELON)
                .lightLevel(state -> state.getValue(ACTIVE) ? 12 : 0));
        registerDefaultState(stateDefinition.any()
                .setValue(ACTIVE, false));
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if(!level.isClientSide && !state.getValue(ACTIVE)) {
            level.setBlockAndUpdate(pos, state.setValue(ACTIVE, true));
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(ACTIVE);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(state.getValue(ACTIVE)) {
            level.setBlockAndUpdate(pos, state.setValue(ACTIVE, false));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        //level.destroyBlock(pos, true);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if(state.getValue(ACTIVE)) ParticleUtils.spawnParticleInBlock(level, pos, 1, ParticleTypes.GLOW_SQUID_INK);
    }
}
