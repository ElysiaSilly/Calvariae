package com.elysiasilly.calvariae.common.item.custom.LexiconCalvariae;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

public class LexiconEntriesDataMap {

    public record LexiconEntries(String entry, String decor) {
        public static final Codec<LexiconEntries> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.fieldOf("entry").forGetter(LexiconEntries::entry),
                Codec.STRING.fieldOf("decor").forGetter(LexiconEntries::decor)
                //Codec.floatRange(0, 1).fieldOf("chance").forGetter(ExampleData::chance)
        ).apply(instance, LexiconEntries::new));
    }

    // In this example, we register the data map for the minecraft:item registry, hence we use Item as the generic.
    // Adjust the types accordingly if you want to create a data map for a different registry.
    public static final DataMapType<Item, LexiconEntries> ENTRIES_DATA = DataMapType.builder(
            // The ID of the data map. Data map files for this data map will be located at
            // <yourmodid>:examplemod/data_maps/item/example_data.json.
            ResourceLocation.fromNamespaceAndPath("lexicon", "entries"),
            // The registry to register the data map for.
            Registries.ITEM,
            // The codec of the data map entries.
            LexiconEntries.CODEC
    ).build();
}
