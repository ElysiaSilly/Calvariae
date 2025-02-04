package com.elysiasilly.calvariae.core.limbo.texturefuckery;

import com.elysiasilly.calvariae.core.Calvariae;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

import java.io.IOException;

@EventBusSubscriber(modid = Calvariae.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ReloadListener {

    @SubscribeEvent
    public static void onReloadListenerEvent(RegisterClientReloadListenersEvent event) throws IOException {
        CreationOfDynamicTextures.create();

    }
}
