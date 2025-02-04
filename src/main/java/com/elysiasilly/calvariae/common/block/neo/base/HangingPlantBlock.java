package com.elysiasilly.calvariae.common.block.neo.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class HangingPlantBlock extends Block {

    public static final EnumProperty<State> PART = EnumProperty.create("part", State.class);

    public static final Map<State, VoxelShape> SHAPES = Map.of(
            State.HEAD, Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0),
            State.BODY, Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
    );

    public HangingPlantBlock(Properties properties) {
        super(properties
                .noCollission()
                .instabreak()
                .pushReaction(PushReaction.DESTROY)
        );
        registerDefaultState(getStateDefinition().any()
                .setValue(PART, State.HEAD)
        );
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockState state = defaultBlockState();
        BlockPos pos = context.getClickedPos();
        BlockState aboveState = level.getBlockState(pos.above());

        return aboveState.isFaceSturdy(level, pos, Direction.UP) || aboveState.is(this) ? state : null;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(PART));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {

        if(direction.equals(Direction.DOWN)) {
            state = state.setValue(PART, neighborState.is(this) ? State.BODY : State.HEAD);
        }

        if(direction.equals(Direction.UP)) {
            if(!(neighborState.isFaceSturdy(level, pos, Direction.UP) || neighborState.is(this))) {
                state = Blocks.AIR.defaultBlockState();
            }
        }

        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PART);
    }

    public enum State implements StringRepresentable {
        BODY("body"),
        HEAD("head");

        public final String NAME;

        State(String name) {
            NAME = name;
        }

        @Override
        public String getSerializedName() {
            return this.NAME;
        }
    }
}
