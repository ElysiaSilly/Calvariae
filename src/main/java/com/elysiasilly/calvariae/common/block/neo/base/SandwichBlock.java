package com.elysiasilly.calvariae.common.block.neo.base;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class SandwichBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final EnumProperty<State> SANDWICH = EnumProperty.create("sandwich", State.class);

    public SandwichBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.UP)
                .setValue(SANDWICH, State.HEEL)
        );
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = defaultBlockState();

        state = state.setValue(FACING, context.getClickedFace());

        BlockState clicked = context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace().getOpposite(), 1));

        if(clicked.is(this)) {
            if(clicked.getValue(SANDWICH).equals(State.HEEL) && clicked.getValue(FACING).getAxis().equals(context.getClickedFace().getAxis())) state = state.setValue(SANDWICH, State.CROWN);
        }

        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SANDWICH);
    }

    public enum State implements StringRepresentable {
        CROWN("crown"),
        HEEL("heel");

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
