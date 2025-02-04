package com.elysiasilly.calvariae.core.registry;

import com.elysiasilly.calvariae.core.Calvariae;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class CCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Calvariae.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CALVARIAE = TABS.register("calvariae", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.calvariae"))
            .icon(() -> new ItemStack(CBlocks.VELVET_MOSS_BLOCK.get()))
            .displayItems((parameters, output) -> {
                // gets all items in BlockRegistry
                for (DeferredHolder<Item, ? extends Item> item : CBlocks.BLOCKITEMS.getEntries()) {
                    output.accept(item.get());
                }
                for (DeferredHolder<Item, ? extends Item> item : CItems.ITEMS.getEntries()) {
                    output.accept(item.get());
                }
                /*
                // gets all items in ItemRegistry
                for (DeferredHolder<Item, ? extends Item> item : MUItem.ITEMS.getEntries()) {
                    output.accept(item.get());
                }

                 */
            })
            .build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MATERIAL = TABS.register("neo_calvariae", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.neo_calvariae"))
            .icon(() -> new ItemStack(CItems.LEXICON_CALVARIAE.get()))
            .displayItems((parameters, output) -> output.acceptAll(list(

                    CItems.LANTERN_FLARE,
                    CBlocks.LANTERN_KELP_HEAD,
                    CItems.LANTERN_BULB_SLICE,
                    CBlocks.LANTERN_KELP_BULB,
                    CBlocks.ROTTING_LANTERN_KELP_BULB,

                    CItems.ADEL_BERRY,
                    CBlocks.ADEL_BERRY_BUNCH,
                    CBlocks.ADELINE_BLOCK,
                    CBlocks.ENGRAVED_ADELINE,
                    CBlocks.EMBEDDED_ADELINE,
                    CBlocks.RIMMED_ADELINE,

                    CBlocks.STONE_CHEST,
                    CBlocks.DEEPSLATE_PILLAR,
                    CBlocks.DEEPSLATE_LATTICE,

                    Blocks.SCULK,
                    Blocks.SCULK_VEIN,
                    CBlocks.SCULK_FUZZ

            )))
            .build()
    );


    private static List<ItemStack> list(Object...entries) {
        List<ItemStack> list = new ArrayList<>();
        for(Object entry : entries) {
            if(entry instanceof Item item) list.add(item.getDefaultInstance());
            if(entry instanceof Block block) list.add(block.asItem().getDefaultInstance());
            if(entry instanceof Holder<?> holder) {
                if(holder.value() instanceof Item item) list.add(item.getDefaultInstance());
                if(holder.value() instanceof Block block) list.add(block.asItem().getDefaultInstance());
            }
        }
        return list;
    }
}
