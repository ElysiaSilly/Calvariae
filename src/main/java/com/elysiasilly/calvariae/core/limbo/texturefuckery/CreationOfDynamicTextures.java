package com.elysiasilly.calvariae.core.limbo.texturefuckery;

import com.elysiasilly.babel.util.RGBA;
import com.elysiasilly.calvariae.core.limbo.Texture;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.server.packs.resources.ResourceManager;

public class CreationOfDynamicTextures {

    public static NativeImage missing_texture;

    private static final TextureManager textureManager = Minecraft.getInstance().getTextureManager();
    private static final ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();

    public static void create() {

        Texture missingTexture = new Texture(2, 2, "custom_texture");

        RGBA white = new RGBA(255, 255, 255, 0);
        RGBA green = new RGBA(35, 255, 0, 0);

        //missingTexture.drawRectangle(1, 1, 2, 2, white);
        //missingTexture.drawPixel(1, 1, green);
        //missingTexture.drawPixel(2, 2, green);

        missing_texture = missingTexture.get();

        //Texture lexiconPageTexture = new Texture(ResourceLocation.fromNamespaceAndPath(Calvariae.MODID, "textures/gui/1.png"), resourceManager, "page");

        //missing_texture = missingTexture.uploadTexture(textureManager);

    }
}
