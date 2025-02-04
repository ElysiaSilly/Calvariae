package com.elysiasilly.calvariae.client.screen;

import com.elysiasilly.babel.client.screen.BabelScreen;
import com.elysiasilly.babel.client.screen.BabelWidget;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("rawtypes")
public class MineralPouchScreen extends BabelScreen {

    public MineralPouchScreen(@Nullable Object menu) {
        super(null);
    }

    @Override
    public List<BabelWidget<?, ?>> initWidgets() {
        return List.of();
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, float partialTick) {

    }
}
