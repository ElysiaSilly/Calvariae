package com.elysiasilly.calvariae.core.limbo.texturefuckery;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.IOException;

public class TextureUtils {

    //private static final TextureManager textureManager = Minecraft.getInstance().getTextureManager();
    //private static final ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();

    public static NativeImage nativeImageFromResourceLocation(ResourceLocation path, ResourceManager resourceManager) {

        var resource = resourceManager.getResource(path);

        if(resource.isPresent()) {
            try {
                return NativeImage.read(resource.get().open());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.printf("%s isn't present%n", resource);
            return CreationOfDynamicTextures.missing_texture;
        }
    }

}
