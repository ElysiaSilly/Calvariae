package com.elysiasilly.calvariae.common.block.neo.special;

import com.elysiasilly.babel.util.MCUtil;
import com.elysiasilly.calvariae.core.registry.CItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class LanternPasteBlock extends Block implements SimpleWaterloggedBlock {

    private static final Map<Direction, VoxelShape> SHAPES = Map.of(
            Direction.UP,    Block.box(0.0 , 0.0 , 0.0 , 16.0, 1.0 , 16.0),
            Direction.DOWN,  Block.box(0.0 , 15.0, 0.0 , 16.0, 16.0, 16.0),
            Direction.EAST,  Block.box(0.0 , 0.0 , 0.0 , 1.0 , 16.0, 16.0),
            Direction.WEST,  Block.box(15.0, 0.0 , 0.0 , 16.0, 16.0, 16.0),
            Direction.SOUTH, Block.box(0.0 , 0.0 , 0.0 , 16.0, 16.0, 1.0 ),
            Direction.NORTH, Block.box(0.0 , 0.0 , 15.0, 16.0, 16.0, 16.0)
    );

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return SHAPES.get(state.getValue(FACING));
    }

    public LanternPasteBlock() {
        super(Properties.of()
                .lightLevel(state -> 12)
                .noOcclusion()
                .noCollission()
                .pushReaction(PushReaction.DESTROY)
                .replaceable()
                .instabreak()
        );
        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.UP)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        Direction dir = context.getClickedFace();

        BlockPos pos = context.getClickedPos(), translatedPos = pos.relative(dir, -1);

        BlockState state = defaultBlockState(), clickedState = level.getBlockState(pos.relative(dir.getOpposite()));

        if(!level.getBlockState(pos).canBeReplaced() || !clickedState.isFaceSturdy(level, translatedPos, dir)) return null;

        state = state.setValue(FACING, dir);
        state = state.setValue(WATERLOGGED, level.getFluidState(pos).is(Fluids.WATER));

        return state;
    }

    private boolean canAttachTo(BlockGetter blockGetter, BlockPos pos, Direction direction) {
        return blockGetter.getBlockState(pos).isFaceSturdy(blockGetter, pos, direction);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        return canAttachTo(level, pos.relative(direction.getOpposite()), direction);
    }

    @Override
    public @NotNull Item asItem() {
        return CItems.LANTERN_BULB_SLICE.asItem();
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
        if(state.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        return facing.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        MCUtil.particle.add(level, ParticleTypes.GLOW, pos.getCenter());
    }
}
