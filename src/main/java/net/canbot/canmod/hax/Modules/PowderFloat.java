package net.canbot.canmod.hax.Modules;

import net.canbot.canmod.event.KeyInputHandler;
import net.canbot.canmod.hax.Module;
import net.canbot.canmod.hax.ModuleManager;
import net.canbot.canmod.mixin.PlayerMoveC2SPacketAccessor;
import net.canbot.canmod.mixin.VehicleMoveC2SPacketAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class PowderFloat extends Module {
    public PowderFloat() {

        super("PowderFloat", KeyInputHandler.registerNewBind("Movement", "PowderFloat", -1), Category.MOVEMENT);

    }
    private static Module module = ModuleManager.getModulebyName("PowderFloat");
    public static boolean powderMixin(Entity entity) {
        if (entity == mc.player && module.toggled) {return true;}

        return false;
    }
static int tickcount = 1;

    @Override
    public void onPacketSend(Packet p) {
        if (p instanceof PlayerMoveC2SPacket) {
            int x = (int) Math.floor(mc.player.getX());
            int y = (int) (mc.player.getY() - mc.player.getHeightOffset());
            int z = (int) Math.floor(mc.player.getZ());



            if (mc.world.getBlockState(new BlockPos(x, y - 1, z)).getBlock().getName().getString().equals("Powder Snow")) {
                tickcount+=1;
                if(tickcount>20) {tickcount=1;}
                if (tickcount == 3) {} else {
                double pY = ((PlayerMoveC2SPacket) p).getY(1);
                ((PlayerMoveC2SPacketAccessor) p).setY(y+0.15);
                }

            }
        }
    }
}
