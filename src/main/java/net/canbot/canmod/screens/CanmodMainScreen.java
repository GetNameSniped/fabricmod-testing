package net.canbot.canmod.screens;

import net.canbot.canmod.CanMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.text.Text;

public class CanmodMainScreen extends Screen {
    MinecraftClient mc = MinecraftClient.getInstance();
    public Screen currentScreen;
    public CanmodMainScreen() {
        super(Text.of("CanMod Main Menu"));


        GridWidget grid = new GridWidget();
        grid.getMainPositioner().margin(2, 1, 2, 1);
        GridWidget.Adder adder = grid.createAdder(6);
        adder.add(ButtonWidget.builder(Text.of("Modules"), b -> {
            if (mc!=null) { mc.setScreen(new ModuleScreen());}
        }).build());

        grid.forEachChild(this::addDrawableChild);

    }



}
