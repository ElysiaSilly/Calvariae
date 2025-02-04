package com.elysiasilly.calvariae.client.screen.lexicon;

import com.elysiasilly.babel.client.screen.BabelScreen;
import com.elysiasilly.babel.client.screen.BabelWidget;
import com.elysiasilly.babel.client.screen.IModifyCameraScreen;
import com.elysiasilly.calvariae.client.lexicon.TestPage;
import net.minecraft.client.Camera;
import net.minecraft.client.gui.GuiGraphics;

import java.util.List;

@SuppressWarnings("rawtypes")
public class LexiconScreen extends BabelScreen implements IModifyCameraScreen {

    @SuppressWarnings("unchecked")
    public LexiconScreen() {
        super(null);
    }

    @Override
    public List<BabelWidget<?, ?>> initWidgets() {
        return List.of();
    }

    TestPage page = new TestPage();

    @Override
    public void renderBackground(GuiGraphics guiGraphics, float partialTick) {
        //guiGraphics.fillGradient(0, 0, 10000, 10000, -1072689136, -804253680);

        //page.render(guiGraphics.pose(), guiGraphics.bufferSource(), partialTick);
    }

    @Override
    public void camera(Camera camera) {

    }

    @Override
    public boolean hideElements() {
        return false;
    }
}
