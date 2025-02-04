package com.elysiasilly.calvariae.common.block.neo.special;

import com.elysiasilly.babel.common.block.BabelEntityBlock;
import com.elysiasilly.calvariae.common.blockentity.RottingLanternBulbBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RottingLanternBulbBlock extends BabelEntityBlock {

    protected static final VoxelShape SHAPE = Block.box(1.0, 1.0, 1.0, 15.0, 15.0, 15.0);

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public RottingLanternBulbBlock() {
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if(state.getValue(ACTIVE)) ParticleUtils.spawnParticleInBlock(level, pos, 1, ParticleTypes.GLOW_SQUID_INK);
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new RottingLanternBulbBE(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide) return null;

        return (lvl, pos, st, blockEntity) -> {
            if(blockEntity instanceof RottingLanternBulbBE be) {
                be.tick();
            }
        };
    }
}
