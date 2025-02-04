package com.elysiasilly.calvariae.common.block.neo.special;

import com.elysiasilly.babel.common.block.BabelEntityBlock;
import com.elysiasilly.calvariae.common.blockentity.StoneChestBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class StoneChestBlock extends BabelEntityBlock implements SimpleWaterloggedBlock {

    private static final Map<Boolean, VoxelShape> SHAPES = Map.of(
            true, Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            false, Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 16.0)
    );

    public static final BooleanProperty SEALED = BooleanProperty.create("sealed");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public StoneChestBlock() {
        super(Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE));
        registerDefaultState(stateDefinition.any()
                .setValue(SEALED, true)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(SEALED));
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if(state.getValue(SEALED)) level.setBlock(pos, state.setValue(SEALED, false), 4);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if(!state.getValue(SEALED)) {
            if(level.getBlockEntity(pos) instanceof StoneChestBE be && level instanceof ServerLevel) Containers.dropContents(level, pos, be);
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if(state.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        return state;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if(state.getValue(SEALED)) return InteractionResult.FAIL;

        if(level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else if (level.getBlockEntity(pos) instanceof StoneChestBE be) {
            player.openMenu(be);
        }

        return InteractionResult.CONSUME;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(level.getBlockEntity(pos) instanceof StoneChestBE be) {
            be.recheckOpen();
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StoneChestBE(pos, state);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SEALED, WATERLOGGED);
    }
}
