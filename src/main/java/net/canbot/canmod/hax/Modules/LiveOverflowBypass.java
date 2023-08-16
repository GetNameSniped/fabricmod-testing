package net.canbot.canmod.hax.Modules;


import net.canbot.canmod.CanMod;
import net.canbot.canmod.event.KeyInputHandler;
import net.canbot.canmod.mixin.PlayerMoveC2SPacketAccessor;
import net.canbot.canmod.mixin.VehicleMoveC2SPacketAccessor;
import net.canbot.canmod.hax.Module;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class LiveOverflowBypass extends Module {

    public LiveOverflowBypass() {
        super("IamBot", KeyInputHandler.registerNewBind("Movement", "IamBot", -1), Category.EXPLOIT);
        this.toggle();
        this.showModule=false;
    }

    static double lastGoodX;
    static double lastGoodZ;
    static double lastGoodBoatX;
    static double lastGoodBoatZ;

    @Override

    public void onPacketSend(Packet p) {




        if(p instanceof PlayerMoveC2SPacket){




            double x = ((int)(((PlayerMoveC2SPacket) p).getX(mc.player.getX()) * 100)) / 100.0;
            double z = ((int)(((PlayerMoveC2SPacket) p).getZ(mc.player.getZ()) * 100)) / 100.0;
            if ((x*1000)%10!=0) {
                x = Math.nextAfter(x, x + Math.signum(x));
                z = Math.nextAfter(z, z+Math.signum(z));
            }
            if ((x*1000)%10!=0 || (z*1000)%10!=0 ) {
                x=lastGoodX;
                z=lastGoodZ;
            } else if ( (x*1000)%10==0 || (z*1000)%10==0 ) {
                lastGoodX=x;
                lastGoodZ=z;
            }



            ((PlayerMoveC2SPacketAccessor) p).setX(x);
            ((PlayerMoveC2SPacketAccessor) p).setZ(z);
            //mc.player.sendMessage(Text.of("X: " + x + "Z: " + z), true);

        }

        if(!(mc.player.getVehicle() instanceof BoatEntity boat)){return;}

        if(p instanceof VehicleMoveC2SPacket){
            double boatx = ((int)(((VehicleMoveC2SPacket) p).getX() * 100)) / 100.0;
            double boatz = ((int)(((VehicleMoveC2SPacket) p).getZ() * 100)) / 100.0;
            if ((boatx*1000)%10!=0 || (boatz*1000)%10!=0 ) {
                boatx=lastGoodBoatX;
                boatz=lastGoodBoatZ;
            } else if ( (boatx*1000)%10==0 || (boatz*1000)%10==0 ) {
                lastGoodBoatX=boatx;
                lastGoodBoatZ=boatz;
            }

            ((VehicleMoveC2SPacketAccessor) p).setX(boatx);
            ((VehicleMoveC2SPacketAccessor) p).setZ(boatz);
            //mc.player.sendMessage(Text.of("X: " + boatx + "Z: " + boatz), true);
        }

    }


}