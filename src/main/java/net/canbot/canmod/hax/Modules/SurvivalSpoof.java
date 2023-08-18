package net.canbot.canmod.hax.Modules;

import net.canbot.canmod.event.KeyInputHandler;
import net.canbot.canmod.hax.Module;
import net.canbot.canmod.util.render.Renderer;
import net.canbot.canmod.util.render.WorldRenderer;
import net.canbot.canmod.util.render.color.QuadColor;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;

public class SurvivalSpoof extends Module {
    public static KeyBinding bind = KeyInputHandler.registerNewBind("Misc", "SurvivalSpoof", -1);
    public SurvivalSpoof() {
        super("SurvivalSpoof", bind, Category.RENDER);
    }

    @Override
    public void onEnable() {
        mc.interactionManager.setGameMode(GameMode.SURVIVAL);
        WorldRenderer.drawText(Text.of("Hello"), mc.player.getX(), mc.player.getY(), mc.player.getZ(), 10, true);
        //mc.render
    }
}
