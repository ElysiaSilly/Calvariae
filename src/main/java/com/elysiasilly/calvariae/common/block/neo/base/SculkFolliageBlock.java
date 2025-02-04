package com.elysiasilly.calvariae.common.block.neo.base;

import com.elysiasilly.calvariae.core.registry.CParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SculkFolliageBlock extends Block {

    public static final EnumProperty<RetractionLevel> RETRACTION_LEVEL = EnumProperty.create("retraction_level", RetractionLevel.class);
    public static final BooleanProperty ON_SCULK = BooleanProperty.create("on_sculk");

    public static final Map<RetractionLevel, VoxelShape> SHAPE = Map.of(
            RetractionLevel.UNRETRACTED,         Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0),
            RetractionLevel.PARTIALLY_RETRACTED, Block.box(2.0, 0.0, 2.0, 14.0, 7.0, 14.0),
            RetractionLevel.RETRACTED,           Block.box(2.0, 0.0, 2.0, 14.0, 1.0, 14.0)
    );

    public SculkFolliageBlock(Properties properties) {
        super(properties
                .mapColor(MapColor.PLANT)
                .replaceable()
                .noCollission()
                .instabreak()
                .sound(SoundType.GRASS)
                .offsetType(BlockBehaviour.OffsetType.XYZ)
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY)
        );
        registerDefaultState(defaultBlockState()
                .setValue(RETRACTION_LEVEL, RetractionLevel.UNRETRACTED)
                .setValue(ON_SCULK, false)
        );
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.get(state.getValue(RETRACTION_LEVEL));
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if(!entity.isCrouching() && state.getValue(RETRACTION_LEVEL).equals(RetractionLevel.UNRETRACTED)) {
            level.setBlockAndUpdate(pos, state.setValue(RETRACTION_LEVEL, RetractionLevel.RETRACTED));

            ParticleUtils.spawnParticleInBlock(level, pos, 10, CParticles.WHITE_FLOWER_PARTICLE.get());
            level.levelEvent(2001, pos, getId(state));
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {

        if(neighborState.is(this)) {
            RetractionLevel retraction = neighborState.getValue(RETRACTION_LEVEL);
            if(retraction.equals(RetractionLevel.RETRACTED)) {
                state = state.setValue(RETRACTION_LEVEL, RetractionLevel.PARTIALLY_RETRACTED);
                ParticleUtils.spawnParticleInBlock(level, pos, 10, CParticles.WHITE_FLOWER_PARTICLE.get());
                ParticleUtils.spawnParticleInBlock(level, pos, 10, CParticles.WHITE_FLOWER_PARTICLE.get());
            }
            if(retraction.equals(RetractionLevel.UNRETRACTED) && state.getValue(RETRACTION_LEVEL).equals(RetractionLevel.RETRACTED)) {
                state = state.setValue(RETRACTION_LEVEL, RetractionLevel.PARTIALLY_RETRACTED);
            }
        }

        return state;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = defaultBlockState();

        if(context.getLevel().getBlockState(context.getClickedPos().below()).is(Blocks.SCULK)) state = state.setValue(ON_SCULK, true);

        return state;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return Block.canSupportCenter(level, pos.below(), Direction.UP);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        RetractionLevel retraction = state.getValue(RETRACTION_LEVEL);

        if(retraction.equals(RetractionLevel.RETRACTED)) state = state.setValue(RETRACTION_LEVEL, RetractionLevel.PARTIALLY_RETRACTED);
        if(retraction.equals(RetractionLevel.PARTIALLY_RETRACTED)) state = state.setValue(RETRACTION_LEVEL, RetractionLevel.UNRETRACTED);

        level.setBlockAndUpdate(pos, state);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(RETRACTION_LEVEL).TICK;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(RETRACTION_LEVEL, ON_SCULK);
    }

    public enum RetractionLevel implements StringRepresentable {
        UNRETRACTED("unretracted", false),
        PARTIALLY_RETRACTED("partially_retracted", true),
        RETRACTED("retracted", true);

        private final String NAME;
        private final boolean TICK;

        RetractionLevel(String name, boolean tick) {
            this.NAME = name;
            this.TICK = tick;
        }

        @Override
        public String getSerializedName() {
            return this.NAME;
        }
    }

}
