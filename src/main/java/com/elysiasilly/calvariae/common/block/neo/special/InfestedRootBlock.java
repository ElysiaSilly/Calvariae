package com.elysiasilly.calvariae.common.block.neo.special;

import com.elysiasilly.calvariae.common.block.neo.base.FlammableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.state.BlockState;

public class InfestedRootBlock extends FlammableBlock {

    public InfestedRootBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean dropExperience) {
        if(level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && !EnchantmentHelper.hasTag(stack, EnchantmentTags.PREVENTS_INFESTED_SPAWNS)) {

            int ran = level.random.nextInt(3, 7);

            for(int i = 0; i < ran; i++) {
                Bat bat = EntityType.BAT.create(level);

                if(bat == null) return;

                bat.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.0F, 0.0F);
                level.addFreshEntity(bat);
                bat.spawnAnim();
            }
        }
    }

}
