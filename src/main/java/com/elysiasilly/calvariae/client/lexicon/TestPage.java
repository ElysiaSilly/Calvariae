package com.elysiasilly.calvariae.client.lexicon;

import com.elysiasilly.calvariae.client.lexicon.element.LexiconElement;
import com.elysiasilly.calvariae.client.lexicon.element.TextElement;
import com.elysiasilly.calvariae.core.Calvariae;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class TestPage extends LexiconPage{


    public TestPage() {
        super(
                ResourceLocation.fromNamespaceAndPath("test", "test"),
                List.of(
                        new TextElement(
                                new Vec3(10, 10, 20),
                                20,
                                new Vec2(20, 10),
                                Minecraft.getInstance().font.split(FormattedText.composite(
                                        FormattedText.of("testing 1", Style.EMPTY.withBold(true).withColor(ChatFormatting.DARK_AQUA)),
                                        FormattedText.of("testing 2", Style.EMPTY.withItalic(true).withColor(ChatFormatting.DARK_GRAY)),
                                        FormattedText.of("testing 3", Style.EMPTY.withColor(0)),
                                        FormattedText.of("testing 4", Style.EMPTY.withColor(ChatFormatting.BLUE))),
                                        200
                                )
                        ),
                        new TextElement(
                                new Vec3(30, 99, 20),
                                -5,
                                new Vec2(20, 10),
                                Minecraft.getInstance().font.split(FormattedText.composite(
                                        FormattedText.of("testing 5", Style.EMPTY.withFont(ResourceLocation.withDefaultNamespace("alt")).withBold(true).withColor(ChatFormatting.GOLD)),
                                        FormattedText.of("testing 6", Style.EMPTY.withObfuscated(true).withItalic(true).withColor(ChatFormatting.GREEN))),
                                        200
                                )
                        )
                )
        );
    }
}
