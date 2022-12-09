package net.canbot.canmod.hax.Modules;


import net.canbot.canmod.CanMod;
import net.canbot.canmod.hax.ModuleManager;
import net.canbot.canmod.hax.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Comparator;


public class Hud {
    public static MinecraftClient mc = MinecraftClient.getInstance();
    public static int color = new Color(9, 128, 189, 255).getRGB();


    public static void draw(MatrixStack matrixStack, float tickDelta) {
        mc.textRenderer.drawWithShadow(matrixStack, CanMod.NAME + " [" + mc.getSession().getUsername() + "] ",2,2,color);
        //mc.textRenderer.drawWithShadow(matrixStack, "[XYZ] " + (int)mc.player.getX() +", " + (int)mc.player.getZ(),2,10,color);


        int y = 20;
        ModuleManager.getModules().sort(Comparator.comparingInt((Module module) -> module.getName().length()).reversed());
        for(Module m : ModuleManager.getModules()) {
            if(m.showModule) {
                mc.textRenderer.drawWithShadow(matrixStack, m.getName() + " [" + KeyEvent.getKeyText(m.getBindInt()) + "]", 2, y, m.isToggled() ? color : -1);
                y += mc.textRenderer.fontHeight + 1;
            }
        }
    }

}