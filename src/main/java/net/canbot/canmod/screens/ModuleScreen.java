package net.canbot.canmod.screens;

import net.canbot.canmod.CanMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.text.Text;

public class ModuleScreen extends Screen {
    MinecraftClient mc = CanMod.INSTANCE.mc;
    public ModuleScreen() {
        super(Text.of("Module list"));
        /*GridWidget grid = new GridWidget();
        grid.getMainPositioner().margin(2, 1, 2, 1);
        GridWidget.Adder adder = grid.createAdder(6);
        adder.add(ButtonWidget.builder(Text.of("Test Module"), b -> {
            mc.setScreen(new TestModuleScreen());
        }).width(70).build(), grid.getMainPositioner());
        grid.forEachChild(this::addDrawableChild);*/


    }

    public ButtonWidget mainbuton;

    @Override
    protected void init() {
        System.out.println("Init butone");
        mainbuton = ButtonWidget.builder(Text.of("CanMod Menu"), b -> mc.setScreen(new TestModuleScreen()))
                .dimensions(0, 0, 70, 20)
                .tooltip(Tooltip.of(Text.of("Open The CanMod Menu")))
                .build();
        this.addDrawableChild(mainbuton);
    }
}
