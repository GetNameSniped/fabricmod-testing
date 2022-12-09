package net.canbot.canmod.hax.Modules;

import net.canbot.canmod.event.KeyInputHandler;
import net.canbot.canmod.hax.Module;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class Flight extends Module {

    public static KeyBinding bind = KeyInputHandler.registerNewBind("Movement", "Flight", -1);
    public Flight() {
        super("Flight", bind, Category.MOVEMENT);
    }

    @Override

    public void onTick() {

        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed(0.5f);
        mc.player.setSprinting(true);
        double yMotion = 0;

        if (mc.player.age % 4 == 0) {
            mc.player.setVelocity(mc.player.getVelocity().x,mc.player.getVelocity().y - 0.04,mc.player.getVelocity().z);
        }

        if(mc.player.input.jumping){
            yMotion+=0.02;
        }
        if(mc.player.input.sneaking){
            yMotion-=0.02;
        }

        mc.player.setVelocity(mc.player.getVelocity().x,mc.player.getVelocity().y + yMotion,mc.player.getVelocity().z);
    }
    @Override
    public void onDisable() {
        mc.player.getAbilities().flying = false;
    }

}
