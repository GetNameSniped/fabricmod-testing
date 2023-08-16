package net.canbot.canmod.screens;

import net.canbot.canmod.CanMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.text.Text;

public class TestModuleScreen extends Screen {
    MinecraftClient mc = CanMod.INSTANCE.mc;
    public TestModuleScreen() {
        super(Text.of("Testing module"));
        GridWidget grid = new GridWidget();
        grid.getMainPositioner().margin(2, 1, 2, 1);
        GridWidget.Adder adder = grid.createAdder(6);
        adder.add(ButtonWidget.builder(Text.of("Enabled: false"), b -> {
            mc.setScreen(new TestModuleScreen());
        }).width(70).build(), grid.getMainPositioner());
        grid.forEachChild(this::addDrawableChild);
    }
}
