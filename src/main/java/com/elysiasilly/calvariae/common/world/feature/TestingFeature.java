package com.elysiasilly.calvariae.common.world.feature;

import com.elysiasilly.calvariae.core.registry.CBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class TestingFeature extends Feature<NoneFeatureConfiguration> {
    public TestingFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        WorldGenLevel level = context.level();
        BlockPos originPos = context.origin();
        originPos = level.getChunk(originPos).getPos().getBlockAt(0, originPos.getY(), 0);

        Dictionary<BlockPos, BlockPos> surface = new Hashtable<>();

        for(int i = -2; i < 18; ++i) {
            for(int j = -2; j < 18; ++j) {

                int k = originPos.getX() + i;
                int l = originPos.getZ() + j;

                BlockPos currentPos = originPos.offset(i, 0, j);

                if(level.getBlockState(currentPos).canBeReplaced()) {
                    while(!level.getBlockState(currentPos).isSolid()) {
                        currentPos = currentPos.below();
                    }

                    if(!level.getBlockState(currentPos.above()).is(Blocks.WATER) && !level.getBlockState(currentPos.above()).is(Blocks.LAVA) && !level.getBlockState(currentPos).is(Blocks.MOSS_BLOCK)) {
                        surface.put(new BlockPos(k, 0, l), currentPos);
                    }
                }
            }
        }

        Enumeration<BlockPos> k = surface.keys();

        while (k.hasMoreElements()) {

            BlockPos key = k.nextElement();
            BlockPos pos = surface.get(key);

            if(
                    pos.getX() != originPos.offset(16, 0 ,0).getX()
                            &&
                    pos.getX() != originPos.offset(-1, 0 ,0).getX()
                            &&
                    pos.getZ() != originPos.offset(0, 0 ,16).getZ()
                            &&
                    pos.getZ() != originPos.offset(0, 0 ,-1).getZ()
                            &&
                    pos.getX() != originPos.offset(17, 0 ,0).getX()
                            &&
                    pos.getX() != originPos.offset(-2, 0 ,0).getX()
                            &&
                    pos.getZ() != originPos.offset(0, 0 ,17).getZ()
                            &&
                    pos.getZ() != originPos.offset(0, 0 ,-2).getZ()
            ) {

                setBlock(level, pos, Blocks.MOSS_BLOCK.defaultBlockState());

                int help = 0;
                int help2 = pos.getY() * 4;

                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (Math.abs(j) != Math.abs(i)) {
                            BlockPos tempPos = surface.get(new BlockPos(key.offset(i, 0, j)));

                            if (tempPos != null)
                                help = help + tempPos.getY();
                        }
                    }
                }

                int help3 = 0;
                int help4 = pos.getY() * 16;

                for (int i = -2; i < 3; i++) {
                    for (int j = -2; j < 3; j++) {
                        if (Math.abs(j) != Math.abs(i)) {
                            BlockPos tempPos = surface.get(new BlockPos(key.offset(i, 0, j)));

                            if (tempPos != null)
                                help3 = help3 + tempPos.getY();
                        }
                    }
                }

                if (help == help2) {
                    setBlock(level, pos.above(), CBlocks.SHORT_MOSSPROUT.get().defaultBlockState());
                    //setBlock(level, pos, Blocks.RED_CONCRETE.defaultBlockState());
                }

                int random = level.getRandom().nextIntBetweenInclusive(1, 4);

                if (help3 > help4) {
                    if (random == 4 || random == 3)
                        setBlock(level, pos.above(), CBlocks.AZALEA_PETALS.get().defaultBlockState().setValue(BlockStateProperties.DOWN, true));
                }

                if (help3 == help4) {
                    switch (random) {
                        case 1    -> {
                            setBlock(level, pos.above(), CBlocks.TALL_MOSSSPROUT.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
                            setBlock(level, pos.above(2), CBlocks.TALL_MOSSSPROUT.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
                        }
                        case 2, 3 -> {
                            setBlock(level, pos.above(), CBlocks.SHORT_MOSSPROUT.get().defaultBlockState());
                        }
                        case 4    -> {
                            setBlock(level, pos.above(), CBlocks.FLOWERING_MOSSPROUT.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
                            setBlock(level, pos.above(2), CBlocks.FLOWERING_MOSSPROUT.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
                        }
                    }
                }
            }
        }
        return true;
    }
}
