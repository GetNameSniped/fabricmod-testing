package net.canbot.canmod.hax.Modules;

import net.canbot.canmod.event.KeyInputHandler;
import net.canbot.canmod.hax.Module;
import net.canbot.canmod.util.player.Rotations;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Collections;


//THIS IS A DRAFT, THIS IS IN THE MAKING
public class AutoReflect extends Module {
    public AutoReflect() {
        super("AutoReflect", KeyInputHandler.registerNewBind("Combat", "AutoReflect", -1), Category.COMBAT);
    }





    static void hitEntity(Entity target, double yaw, double pitch) {

        mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.LookAndOnGround((float) yaw, (float) pitch, mc.player.isOnGround()));
        mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.attack(target, mc.player.isSneaking()));

    }

    public void sendPacket(double yaw,double pitch, Entity entity) {
        mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.LookAndOnGround((float) yaw, (float) pitch, mc.player.isOnGround()));
        mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.attack(entity, mc.player.isSneaking()));
    }

    @Override
    public void onTick() {
        BlockPos playerPos = mc.player.getBlockPos();
        ArrayList ghastPositions = new ArrayList<>();
       for(Entity entity : mc.world.getEntities()) {
           if (entity instanceof GhastEntity) {
               ghastPositions.add(entity.getBlockPos());
           } else if(entity instanceof FireballEntity) {
               BlockPos fireballPos = entity.getBlockPos();


               double distance = playerPos.getManhattanDistance(fireballPos);
               mc.player.sendMessage(Text.of(String.valueOf(distance)), true);
               if (distance<8) {
                   System.out.println("inrange");
                   if (ghastPositions.size()!=0) {
                    Collections.sort(ghastPositions);

                       hitEntity(entity, Rotations.getYaw((BlockPos) ghastPositions.get(0)), Rotations.getPitch((BlockPos) ghastPositions.get(0)));
                   } else {hitEntity(entity, Rotations.getYaw(entity), Rotations.getPitch(entity));}
               }
           }
       }

    }
}
