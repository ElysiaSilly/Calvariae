package com.elysiasilly.calvariae.common.block.neo.special;

import com.elysiasilly.babel.common.block.api.ITransformOnBurnBlock;
import com.elysiasilly.calvariae.core.registry.CBlocks;
import com.elysiasilly.calvariae.core.registry.CItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbility;

public class SweetrootBlock extends PipeBlock implements SimpleWaterloggedBlock, BonemealableBlock, ITransformOnBurnBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<BambooLeaves> LEAVES = BlockStateProperties.BAMBOO_LEAVES;

    public SweetrootBlock(Properties properties) {
        super(((float) 1 / 16) * 3, properties);
        registerDefaultState(stateDefinition.any()
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(WATERLOGGED, false)
                .setValue(LEAVES, BambooLeaves.NONE)
        );
    }

    @Override
    protected MapCodec<SweetrootBlock> codec() {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED, LEAVES);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = defaultBlockState();

        boolean flag = level.getBlockState(pos.below()).is(this);

        for(Direction dir : Direction.values()) {
            if(!(flag && dir.getAxis().isHorizontal())) {
                state = state.setValue(PROPERTY_BY_DIRECTION.get(dir), canConnect(level.getBlockState(pos.relative(dir))));
            }
        }

        return state;
    }

    // TODO : replace with tag
    public boolean canConnect(BlockState state) {
        return state.is(this) || state.is(CBlocks.SWEETROOT_BUNDLE.get()) || state.is(Blocks.CLAY);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {

        boolean flag = facingState.is(this) ? facingState.getValue(PROPERTY_BY_DIRECTION.get(facing.getOpposite())) : false;

        if(canConnect(facingState) && flag) {
            state = state.setValue(PROPERTY_BY_DIRECTION.get(facing), true);
        }

        if(state.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        return state;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {

        Level level = context.getLevel();
        Vec3 pos = context.getClickedPos().getCenter();
        BlockState temp = null;

        if(context.getItemInHand().getItem() instanceof AxeItem && state.is(CBlocks.SWEETROOT.get())) {
            temp = CBlocks.STRIPPED_SWEETROOT.get().withPropertiesOf(state).setValue(LEAVES, BambooLeaves.NONE);
            level.addFreshEntity(new ItemEntity(level, pos.x, pos.y, pos.z, CItems.SWEETROOT_BARK.toStack()));
        }

        return temp;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public void onBurn(Level level, BlockPos pos, Direction face, BlockState state) {
        if(state.is(CBlocks.SWEETROOT)) level.setBlockAndUpdate(pos, CBlocks.CARAMELISED_SWEETROOT.get().withPropertiesOf(state).setValue(LEAVES, BambooLeaves.NONE));
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos pos, BlockState state) {
        return state.is(CBlocks.SWEETROOT.get()) && !state.getValue(LEAVES).equals(BambooLeaves.LARGE) && (state.getValue(UP) || state.getValue(DOWN));
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos pos, BlockState state) {
        serverLevel.setBlock(pos, state.cycle(LEAVES), 3);
    }
}
