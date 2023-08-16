package net.canbot.canmod.hax.Modules;

import net.canbot.canmod.event.KeyInputHandler;
import net.canbot.canmod.hax.Module;
import net.canbot.canmod.util.entity.Target;
import net.canbot.canmod.util.player.Rotations;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class FireballMagic extends Module {

    public FireballMagic() {
        super("FireballMagic", KeyInputHandler.registerNewBind("Misc", "FireballMagic", -1), Category.EXPLOIT);

    }

    static void hitEntity(Entity target, double yaw, double pitch) {
        double firstYaw = mc.player.getYaw();
        double firstPitch = mc.player.getPitch();
        mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.LookAndOnGround((float) yaw, (float) pitch, mc.player.isOnGround()));
        mc.getNetworkHandler().sendPacket(PlayerInteractEntityC2SPacket.attack(target, mc.player.isSneaking()));
        mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.LookAndOnGround((float) firstYaw, (float) firstPitch, mc.player.isOnGround()));
    }
    static Map<Entity, String> fireballsInRange = new HashMap<Entity, String>();
    static String getState(Entity entity) {
        if (fireballsInRange.isEmpty()) {return "EMPTY";} else {return (String) fireballsInRange.get(entity);}
    }
    static void setState(Entity entity, String state) {
        fireballsInRange.put(entity, state);
    }
    static void removeOutOfRange() {
        if (!fireballsInRange.isEmpty()) {
            for (var entity : fireballsInRange.entrySet()) {
                if (entity.getKey().getBlockPos().getManhattanDistance(mc.player.getBlockPos()) > 8) {
                    fireballsInRange.remove(entity);
                }
            }
        }
    }

    static void addToMap(Entity entity) {if (fireballsInRange.get(entity) == null) {
        fireballsInRange.put(entity, "EMPTY");
    }}

    @Override
    public void onTick() {
        BlockPos playerPos = mc.player.getBlockPos();
        for (Entity entity : mc.world.getEntities()) {
            if (entity instanceof FireballEntity) {
                removeOutOfRange();
                BlockPos fireballPos = entity.getBlockPos();
                if (fireballPos.getManhattanDistance(mc.player.getBlockPos()) < 8) {
                    addToMap(entity);
                    mc.player.sendMessage(Text.of(String.valueOf(fireballPos.getManhattanDistance(mc.player.getBlockPos()))), true);
                    if (getState(entity) == "EMPTY") {
                        hitEntity(entity, Rotations.getYaw(entity), Rotations.getPitch(entity));setState(entity, "GOPLACE");}
                    else if(getState(entity)=="GOPLACE") {
                        BlockPos dest = mc.player.getBlockPos().offset(Direction.Axis.Y, 5);
                        hitEntity(entity, Rotations.getYawFromTo(entity, dest), Rotations.getPitchFromTo(entity, dest));
                        //mc.player.sendMessage(Text.of("YAW: "+ Rotations.getYawFromTo(entity, dest) + " PITCH: " + Rotations.getPitchFromTo(entity, dest)));
                        //setState(entity, "HIT");
                    }
                }

            }
        }

    }
}
