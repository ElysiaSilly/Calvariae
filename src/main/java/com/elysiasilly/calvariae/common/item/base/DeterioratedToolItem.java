package com.elysiasilly.calvariae.common.item.base;

import com.elysiasilly.calvariae.common.item.base.interfaces.IDeterioratedTool;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.common.ItemAbility;

public abstract class DeterioratedToolItem extends Item implements IDeterioratedTool {
    public DeterioratedToolItem() {
        super(new Item.Properties().durability(64).stacksTo(1).rarity(Rarity.UNCOMMON).component(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 0.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, 2.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public abstract boolean canPerformAction(ItemStack stack, ItemAbility itemAbility);

    /*
    @Override
    public TagKey<Item> tagFilter() {
        return ItemTags.SWORDS;
    }

     */

}
