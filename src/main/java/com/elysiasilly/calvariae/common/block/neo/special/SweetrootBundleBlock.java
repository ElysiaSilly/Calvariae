package com.elysiasilly.calvariae.common.block.neo.special;

import com.elysiasilly.babel.common.block.api.ITransformOnBurnBlock;
import com.elysiasilly.calvariae.common.block.neo.base.LogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class SweetrootBundleBlock extends LogBlock implements ITransformOnBurnBlock {

    final Block BURNT;

    public SweetrootBundleBlock(Block stripped, Block burnt) {
        super(stripped, true, Properties.ofFullCopy(Blocks.OAK_LOG));
        this.BURNT = burnt;
    }

    @Override
    public void onBurn(Level level, BlockPos pos, Direction face, BlockState state) {
        level.setBlockAndUpdate(pos, this.BURNT.withPropertiesOf(state));
    }
}
