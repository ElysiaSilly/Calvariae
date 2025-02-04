package com.elysiasilly.calvariae.core.registry;

import com.elysiasilly.calvariae.common.item.custom.AncientSwordItem;
import com.elysiasilly.calvariae.common.item.neo.LexiconItem;
import com.elysiasilly.calvariae.common.item.neo.LanternBulbSliceItem;
import com.elysiasilly.calvariae.common.item.neo.LanternFlareItem;
import com.elysiasilly.calvariae.common.item.neo.MineralPouchItem;
import com.elysiasilly.calvariae.common.item.neo.SweetrootBarkItem;
import com.elysiasilly.calvariae.core.Calvariae;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Calvariae.MODID);

    public static final DeferredItem<Item> ADEL_BERRY = item("adel_berry");

    public static final DeferredItem<Item> LANTERN_FLARE = item("lantern_flare", () -> new LanternFlareItem(prop().stacksTo(8)));

    public static final DeferredItem<Item> MINERAL_POUCH = item("mineral_pouch", () -> new MineralPouchItem(prop().stacksTo(1)));

    public static final DeferredItem<Item> LANTERN_BULB_SLICE = item("lantern_bulb_slice", () -> new LanternBulbSliceItem(prop()));

    public static final DeferredItem<Item> SWEETROOT_BARK = ITEMS.register("sweetroot_bark", () ->
            new SweetrootBarkItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(0)
                            .saturationModifier(0)
                            .alwaysEdible()
                            .build()
                    )
            )
    );

    public static final DeferredItem<Item> TOOTH = item("tooth");

    public static final DeferredItem<Item> DETERIORATED_TOOL = ITEMS.register("deteriorated_sword", () ->
            new AncientSwordItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.UNCOMMON)
                    .component(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY)
                    .attributes(AncientSwordItem.createAttributes(0, 0))
            )
    );

    public static final DeferredItem<Item> LEXICON_CALVARIAE = ITEMS.register("lexicon_calvariae", () ->
            new LexiconItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)
                    //.component(CComponents.PAGE, PageComponent.EMPTY)
            )
    );

    private static DeferredItem<Item> item(String id) {
        return ITEMS.registerSimpleItem(id);
    }

    private static DeferredItem<Item> item(String id, Item.Properties properties) {
        return ITEMS.registerSimpleItem(id, properties);
    }

    private static <T extends Item> DeferredItem<T> item(String id, Supplier<T> item) {
        return ITEMS.register(id, item);
    }

    private static Item.Properties prop() {
        return new Item.Properties();
    }
}
