package net.canbot.canmod.hax.Modules;

import net.canbot.canmod.event.KeyInputHandler;
import net.canbot.canmod.hax.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.lwjgl.glfw.GLFW;

public class NetherClip extends Module {
    public NetherClip() {
        super("NetherClip", KeyInputHandler.registerNewBind("Movement", "NetherClip", -1), Category.EXPLOIT);

    }

    @Override
    public void onTick()  {
        assert mc.player != null;
        if(mc.player.input.jumping) {
            for (float tp = 0; tp < 2; tp += 0.15) {
                mc.player.setPos(mc.player.getX(), mc.player.getY() + tp, mc.player.getZ());
            }
        }
        if(mc.player.input.sneaking) {
            for (float tp = 0; tp < 1; tp += 0.15) {
                mc.player.setPos(mc.player.getX(), mc.player.getY() - tp, mc.player.getZ());
            }
        }
    }
}