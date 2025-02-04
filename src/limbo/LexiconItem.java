package com.elysiasilly.calvariae.common.item.custom.LexiconCalvariae;

import com.elysiasilly.calvariae.events.LexiconRenderer;
import com.elysiasilly.calvariae.core.registry.ModComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Set;

public class LexiconItem extends Item {

    public LexiconItem(Item.Properties properties) {
        super(properties);

    }

    public Set<LexiconEntriesDataMap.LexiconEntries> entries;

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        ItemStack stack = player.getMainHandItem();

        //ItemStack itemstack = player.getItemInHand(usedHand);

        MenuProvider menu = new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.translatable("container.crafting");
            }

            @Override
            public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                return new LexiconMenu(i);
            }
        };

        player.openMenu(menu);

        //player.openMenu(itemstack, usedHand);



        //LexiconRenderer.entries

        if(stack.get(ModComponents.PAGE) == null) {
            stack.set(ModComponents.PAGE, (stack.get(ModComponents.PAGE)).setPage(1));

            LexiconRenderer.currentPage = 1;
        } else {
            int currentPage = stack.get(ModComponents.PAGE).page();

            if(player.isShiftKeyDown()) {
                stack.set(ModComponents.PAGE, (stack.get(ModComponents.PAGE)).setPage(currentPage - 1));

                LexiconRenderer.currentPage = currentPage - 1;

            } else {
                stack.set(ModComponents.PAGE, (stack.get(ModComponents.PAGE)).setPage(currentPage + 1));

                LexiconRenderer.currentPage = currentPage + 1;

            }
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        //if(entries == null) return;

        if(entity instanceof Player player) {

            if(isSelected) {
                ItemStack itemStack = player.getInventory().getItem(1);

                Holder<Item> holder = itemStack.getItemHolder();
                LexiconEntriesDataMap.LexiconEntries data = holder.getData(LexiconEntriesDataMap.ENTRIES_DATA);

                if (data == null) return;

                LexiconRenderer.entries.add(data);
            } else {
                //LexiconRenderer.entries.clear();
            }
        }

        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        if (stack.get(ModComponents.PAGE) != null) {
            int page = stack.get(ModComponents.PAGE).page();

            MutableComponent component = Component.literal("Page:")
                    .append(" ").append(Integer.toString(page));

            tooltip.add(component.withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY));
        }
        super.appendHoverText(stack, context, tooltip, tooltipFlag);
    }
}
