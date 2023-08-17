package net.canbot.canmod.screens;

import net.canbot.canmod.CanMod;
import net.canbot.canmod.hax.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.text.Text;

public class ModuleScreen extends Screen {
    MinecraftClient mc = MinecraftClient.getInstance();
    public ModuleScreen() {
        super(Text.of("Module list"));
        GridWidget grid = new GridWidget();

        grid.getMainPositioner().margin(4, 4, 0, 0);
        grid.setColumnSpacing(75);
        grid.getMainPositioner().alignLeft();

        GridWidget.Adder adder = grid.createAdder(1);
        ModuleManager.getModules().forEach((module -> {
            adder.add(ButtonWidget.builder(Text.of("Placeholder"), b -> {
                mc.setScreen(new TestModuleScreen());
            }).width(70).build(), 1 ,grid.getMainPositioner());
        }));

        grid.setPosition(0, 0);
        grid.refreshPositions();
        grid.forEachChild(this::addDrawableChild);



    }


}
