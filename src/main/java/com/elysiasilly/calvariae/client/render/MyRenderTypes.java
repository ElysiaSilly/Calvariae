package com.elysiasilly.calvariae.client.render;

import com.elysiasilly.calvariae.core.Calvariae;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;


public class MyRenderTypes {

    public static RenderType testingShader() {
        return CustomRenderTypes.testingShader();
    }

    public static ShaderInstance testingInstance() {
        return CustomRenderTypes.testingShader;
    }

    @EventBusSubscriber(modid = Calvariae.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModClientEvents {

        @SubscribeEvent
        public static void shaderRegistry(RegisterShadersEvent event) throws IOException {
            // Adds a shader to the list, the callback runs when loading is complete.
            event.registerShader(new ShaderInstance(
                    event.getResourceProvider(),
                    ResourceLocation.fromNamespaceAndPath(Calvariae.MODID, "testing_shader"),
                    DefaultVertexFormat.POSITION),
                    shaderInstance -> {
                CustomRenderTypes.testingShader = shaderInstance;
            });
        }
    }

    private static class CustomRenderTypes extends RenderType
    {
        // Holds the object loaded via RegisterShadersEvent
        private static ShaderInstance testingShader;

        // Shader state for use in the render type, the supplier ensures it updates automatically with resource reloads
        private static final ShaderStateShard RENDERTYPE_TESTING_SHADER = new ShaderStateShard(() -> testingShader);

        // Dummy constructor needed to make java happy
        private CustomRenderTypes(String s, VertexFormat v, VertexFormat.Mode m, int i, boolean b, boolean b2, Runnable r, Runnable r2) {
            super(s, v, m, i, b, b2, r, r2); throw new IllegalStateException("This class is not meant to be constructed!");
        }

        // The memoize caches the output value for each input, meaning the expensive registration process doesn't have to rerun
        //public static Function<ResourceLocation, RenderType> TESTING_SHADER = Util.memoize(CustomRenderTypes::testingShader);

        // Defines the RenderType. Make sure the name is unique by including your MODID in the name.
        private static RenderType testingShader() {//ResourceLocation locationIn) {

            RenderType.CompositeState rendertype$state = RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_TESTING_SHADER)
                    .setTextureState(MultiTextureStateShard.builder()
                            .add(ResourceLocation.fromNamespaceAndPath(Calvariae.MODID, "textures/block/velvet_moss/moss.png"), false, false)
                            .build())
                    .createCompositeState(false);
            return create(
                    Calvariae.prefix("testing_shader"),
                    DefaultVertexFormat.POSITION_TEX,
                    VertexFormat.Mode.QUADS,
                    1536,
                    false,
                    false,
                    rendertype$state
            );
        }
    }
}
