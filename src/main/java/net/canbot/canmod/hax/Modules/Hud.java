package net.canbot.canmod.hax.Modules;


import net.canbot.canmod.CanMod;
import net.canbot.canmod.hax.ModuleManager;
import net.canbot.canmod.hax.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Comparator;
import java.util.List;


public class Hud {
    public static MinecraftClient mc = MinecraftClient.getInstance();
    public static int color = new Color(9, 128, 189, 255).getRGB();


    public static void draw(DrawContext context, float tickDelta) {


        int coordsHeight = mc.getWindow().getScaledHeight();

        context.drawTextWithShadow(mc.textRenderer, CanMod.NAME + " [" + mc.getSession().getUsername() + "] ",2,2,color);
        context.drawTextWithShadow(mc.textRenderer, "[XYZ] " + (int)mc.player.getX() +", " + (int)mc.player.getZ(),2,coordsHeight-10,color);



        int y = 25;


        for(Module m : CanMod.sortedModules) {
            if(m.showModule && m.toggled) {
                context.drawTextWithShadow(mc.textRenderer, m.getName() + " [" + KeyEvent.getKeyText(m.getBindInt()) + "]", 2, y, m.isToggled() ? color : -1);
                y += mc.textRenderer.fontHeight + 1;
            }
        }
    }

}