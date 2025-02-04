package com.elysiasilly.calvariae.client.lexicon;

import com.elysiasilly.babel.util.RGBA;
import com.elysiasilly.calvariae.core.Calvariae;
import com.elysiasilly.calvariae.core.limbo.Texture;
import com.elysiasilly.calvariae.core.limbo.texturefuckery.CreationOfDynamicTextures;
import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.datafixers.util.Pair;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.*;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

@EventBusSubscriber(modid = Calvariae.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class LexiconResources extends AbstractPackResources implements PreparableReloadListener {

    public static final PackLocationInfo INFO = new PackLocationInfo("lexicon", Component.translatable("lexicon"), PackSource.BUILT_IN, Optional.empty());

    public static final Set<String> NAMESPACES = ImmutableSet.of("lexicon");

    final PackMetadataSection packInfo;

    public LexiconResources() {
        super(INFO);
        this.packInfo = new PackMetadataSection(
                Component.literal("Lexicon Baked Assets"),
                SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES)
        );
    }

    @SuppressWarnings(" unchecked")
    @Override
    public <T> T getMetadataSection(MetadataSectionSerializer<T> serializer) {
        return serializer == PackMetadataSection.TYPE ? (T) this.packInfo : null;
    }

    @Override
    public @Nullable IoSupplier<InputStream> getRootResource(String... strings) {
        return null;
    }

    @Override
    public @Nullable IoSupplier<InputStream> getResource(PackType packType, ResourceLocation resourceLocation) {

        ResourceLocation location = ResourceLocation.fromNamespaceAndPath("lexicon", "textures/block/custom_texture.png");


        if(location.equals(resourceLocation)) {

            IoSupplier<InputStream> streamGetter = () -> new ByteArrayInputStream(parse().get().asByteArray());
            if (streamGetter == null) {
                return null;
            }

            try {
                return streamGetter;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    Texture parse() {

        /*
        Texture texture = new Texture(ResourceLocation.withDefaultNamespace("textures/block/diamond_block.png"), Minecraft.getInstance().getResourceManager(), "1");

        Texture overlay = new Texture(ResourceLocation.withDefaultNamespace("textures/item/carrot.png"), Minecraft.getInstance().getResourceManager(), "2");

        RGBA colour = texture.getColourAtPixel(1, 1);
        colour.shade(2);

        for(int x = 1; x <= 16; x++) {
            for(int y = 1; y <= 16; y++) {

                RGBA pixel = overlay.getColourAtPixel(x, y);

                if(pixel.alpha != 0) {
                    texture.drawPixel(x, y, colour);
                }
            }
        }

         */

        Texture texture = new Texture(128, 128, "test");

        for(int x = 1; x <= 128; x++) {
            for(int y = 1; y <= 128; y++) {

                if((y & 1) == 0 && (x & 1) == 0) {
                    texture.drawPixel(x, y, RGBA.colours.BLACK);
                }
            }
        }

        return texture;
    }

    @Override
    public void listResources(PackType packType, String namespace, String id, ResourceOutput resourceOutput) {

        ResourceLocation location = ResourceLocation.fromNamespaceAndPath("lexicon", "textures/block/custom_texture.png");

        if(namespace.equals("lexicon") && location.getPath().startsWith(id)) {
            resourceOutput.accept(location, () -> new ByteArrayInputStream(parse().get().asByteArray()));
        }
    }

    @Override
    public Set<String> getNamespaces(PackType packType) {
        return NAMESPACES;
    }

    @Override
    public void close() {

    }

    @Override
    public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller1, Executor workerExecutor, Executor mainExecutor) {
        return CompletableFuture.supplyAsync(() -> null, workerExecutor)
                .thenCompose(preparationBarrier::wait)
                .thenAcceptAsync((noResult) -> {}, mainExecutor);
    }

    public static final LexiconResources PACK = new LexiconResources();

    @SubscribeEvent
    public static void addPackFinders(AddPackFindersEvent event) {
        event.addRepositorySource(
                (infoConsumer) -> infoConsumer
                        .accept(Pack.readMetaAndCreate(LexiconResources.INFO, new Pack.ResourcesSupplier() {
                            @Override
                            public PackResources openPrimary(PackLocationInfo locationInfo) {
                                return PACK;
                            }

                            @Override
                            public PackResources openFull(PackLocationInfo locationInfo, Pack.Metadata metadata) {
                                return PACK;
                            }
                        }, PackType.CLIENT_RESOURCES, new PackSelectionConfig(true, Pack.Position.TOP, false)))
        );
    }

    @SubscribeEvent
    public static void registerReloadListener(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(PACK);
    }
}
