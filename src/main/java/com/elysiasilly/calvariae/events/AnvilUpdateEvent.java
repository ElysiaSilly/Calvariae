package com.elysiasilly.calvariae.events;

import com.elysiasilly.calvariae.common.item.custom.AncientSwordItem;
import com.elysiasilly.calvariae.core.Calvariae;
import com.elysiasilly.calvariae.core.Config;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Calvariae.MODID)
@SuppressWarnings("unused")

public class AnvilUpdateEvent {

    @SubscribeEvent
    public static void anvil(net.neoforged.neoforge.event.AnvilUpdateEvent event) {

        ItemStack leftSlot = event.getLeft();
        ItemStack rightSlot = event.getRight();

        if(leftSlot.getItem() instanceof AncientSwordItem) { // TODO : change to IDeterioratedTool
            ItemStack output = event.getOutput();

            event.setCost(Config.DETERIORATED_TOOL_COST.get());
            event.setOutput(output);
        }



        if(rightSlot.getItem() instanceof AncientSwordItem) {
            if(!leftSlot.is(ItemTags.SWORDS)) {
                event.setOutput(ItemStack.EMPTY);
                //System.out.println("I am not a Sword!");
            }
        }
    }
}
