package com.elysiasilly.calvariae.common.item.neo;

import com.elysiasilly.calvariae.common.entity.LanternBulbSliceEntity;
import com.elysiasilly.calvariae.core.registry.CBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class LanternBulbSliceItem extends Item implements ProjectileItem{


    public LanternBulbSliceItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Direction dir = context.getClickedFace();
        Player player = context.getPlayer();

        BlockPos pos = context.getClickedPos(), relativePos = pos.relative(dir, 1);

        BlockState state = CBlocks.LANTERN_PASTE.get().getStateForPlacement(new BlockPlaceContext(context));


        if(state != null) {
            level.setBlockAndUpdate(relativePos, state);
            if(player != null) {
                if(!player.hasInfiniteMaterials()) {
                    context.getItemInHand().shrink(1);
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand hand) {

        ItemStack heldStack = player.getItemInHand(hand);

        if(false) {
            if(!level.isClientSide) {
                LanternBulbSliceEntity projectile = new LanternBulbSliceEntity(level, player);
                projectile.setItem(heldStack);

                projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 2);
                level.addFreshEntity(projectile);
            }

            player.awardStat(Stats.ITEM_USED.get(this));

            if(!player.hasInfiniteMaterials()) heldStack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(heldStack, level.isClientSide());
    }


    @Override
    public @NotNull Projectile asProjectile(@NotNull Level pLevel, Position pPos, @NotNull ItemStack pStack, @NotNull Direction pDirection) {
        LanternBulbSliceEntity slice = new LanternBulbSliceEntity(pLevel, pPos.x(), pPos.y(), pPos.z(), 1);
        slice.setItem(pStack);
        return slice;
    }
}
