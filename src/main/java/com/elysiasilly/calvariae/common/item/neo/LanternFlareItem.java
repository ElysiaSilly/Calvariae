package com.elysiasilly.calvariae.common.item.neo;

import com.elysiasilly.calvariae.common.entity.LanternFlareEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LanternFlareItem extends Item {

    public LanternFlareItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);

        return super.use(level, player, usedHand);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeCharged) {

        if(!level.isClientSide) {
            LanternFlareEntity projectile = new LanternFlareEntity(level);

            Vec3 pos = livingEntity.getEyePosition();
            Vec3 dir = livingEntity.getLookAngle();
            projectile.setPos(pos);
            projectile.setDeltaMovement(dir);
            //projectile.getGravity()
            //projectile.shootFromRotation(livingEntity, (float) dir.x, (float) dir.y, (float) dir.z, 1, 1);

            level.addFreshEntity(projectile);
        }

        if(!livingEntity.hasInfiniteMaterials()) stack.shrink(1);

        if(livingEntity instanceof Player player) {
            //player.getCooldowns().addCooldown(this, 60);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 120;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }
}
