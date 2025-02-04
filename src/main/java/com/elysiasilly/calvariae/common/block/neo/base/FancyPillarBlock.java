package com.elysiasilly.calvariae.common.block.neo.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class FancyPillarBlock extends Block {

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

    public FancyPillarBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(AXIS, Direction.Axis.Y)
                .setValue(UP, false)
                .setValue(DOWN, false)
        );
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rot) {
        return RotatedPillarBlock.rotatePillar(state, rot);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS, UP, DOWN);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction.Axis axis = context.getClickedFace().getAxis();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        BlockState state = defaultBlockState().setValue(AXIS, axis);

        BlockState above = level.getBlockState(pos.relative(axis, 1));
        BlockState below = level.getBlockState(pos.relative(axis, -1));

        state = state.setValue(UP, above.is(this) && above.getValue(AXIS) == axis);
        state = state.setValue(DOWN, below.is(this) && below.getValue(AXIS) == axis);

        return state;
    }


    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        Direction.Axis axis = state.getValue(AXIS);

        if(direction.getAxis() == axis) {
            if(neighborPos.equals(pos.relative(axis, 1))) {
                state = state.setValue(UP, neighborState.is(this) && neighborState.getValue(AXIS) == axis);
            }
            if(neighborPos.equals(pos.relative(axis, -1))) {
                state = state.setValue(DOWN, neighborState.is(this) && neighborState.getValue(AXIS) == axis);
            }
        }

        return state;
    }
}
