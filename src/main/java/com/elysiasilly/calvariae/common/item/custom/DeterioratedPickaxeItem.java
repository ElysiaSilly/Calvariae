package com.elysiasilly.calvariae.common.item.custom;

import com.elysiasilly.calvariae.common.item.base.DeterioratedToolItem;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class DeterioratedPickaxeItem extends DeterioratedToolItem {

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(itemAbility);
    }

    @Override
    public TagKey<Item> tagFilter() {
        return ItemTags.PICKAXES;
    }
}
