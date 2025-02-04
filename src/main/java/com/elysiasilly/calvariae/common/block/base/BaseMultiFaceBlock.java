package com.elysiasilly.calvariae.common.block.base;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.MultifaceSpreader;

public class BaseMultiFaceBlock extends MultifaceBlock {

    public static final MapCodec<BaseMultiFaceBlock> CODEC = simpleCodec(BaseMultiFaceBlock::new);
    private final MultifaceSpreader spreader = new MultifaceSpreader(this);

    public BaseMultiFaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends MultifaceBlock> codec() {
        return CODEC;
    }

    @Override
    public MultifaceSpreader getSpreader() {
        return this.spreader;
    }
}
