package net.canbot.canmod.hax.Modules;

import net.canbot.canmod.event.KeyInputHandler;
import net.canbot.canmod.hax.Module;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.world.GameMode;

public class SurvivalSpoof extends Module {
    public static KeyBinding bind = KeyInputHandler.registerNewBind("Misc", "SurvivalSpoof", -1);
    public SurvivalSpoof() {
        super("SurvivalSpoof", bind, Category.RENDER);
    }

    @Override
    public void onEnable() {
        mc.interactionManager.setGameMode(GameMode.SURVIVAL);
    }
}
