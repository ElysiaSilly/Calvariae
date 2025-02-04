package com.elysiasilly.calvariae.core.limbo;

import com.elysiasilly.babel.util.Conversions;
import com.elysiasilly.babel.util.RGBA;
import com.elysiasilly.calvariae.core.limbo.texturefuckery.TextureUtils;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.Closeable;

public class Texture implements Closeable {

    private final NativeImage texture;
    private final String name;
    private DynamicTexture dynamicTexture;
    //private final AnimationMetadataSection metaData;

    public Texture(NativeImage texture, String name) {//, AnimationMetadataSection metaData) { // TODO : metadata support ?
        this.texture = texture;
        this.name = name;
    }

    public Texture(ResourceLocation path, ResourceManager resourceManager, String name) {
        this.texture = TextureUtils.nativeImageFromResourceLocation(path, resourceManager);
        this.name = name;
    }


    public Texture(int width, int height, String name) {
        this.name = name;
        this.texture = new NativeImage(width, height, false); // TODO : what is useCalloc
    }

    public NativeImage get() {
        return this.texture;
    }

    public ResourceLocation uploadTexture(TextureManager manager) {
        dynamicTexture = new DynamicTexture(this.texture);
        dynamicTexture.upload();
        return manager.register(this.name, dynamicTexture);
    }

    public RGBA getColourAtPixel(int x, int y) {
        return Conversions.colour.rgbaA(this.texture.getPixelRGBA(x - 1, y - 1));
        // Contrary to its name, getPixelRGBA returns ABGR
    }

    public void drawPixel(int x, int y, RGBA colour) {
        this.texture.setPixelRGBA(x - 1, y - 1, Conversions.colour.abgr(colour));
    }

    public void drawRectangle(int startX, int startY, int endX, int endY, RGBA colour) {
        for(int x = startX ; x <= endX; x++) {
            for(int y = startY ; y <= endY; y++) {
                drawPixel(x, y, colour);
            }
        }
    }

    public int getHeight() {
        return this.texture.getHeight();
    }

    public int getWidth() {
        return this.texture.getWidth();
    }

    public void nukeTexture() {
        this.texture.close();
    }

    @Override
    public void close() {
        this.texture.close();
        this.dynamicTexture.close();
    }
}
