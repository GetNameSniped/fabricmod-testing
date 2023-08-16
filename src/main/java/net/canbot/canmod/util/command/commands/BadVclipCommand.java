package net.canbot.canmod.util.command.commands;

import net.canbot.canmod.util.command.Command;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

import java.util.List;

public class BadVclipCommand extends Command {


    public BadVclipCommand() {
        super("V-Clip but bad", "bvclip");
    }

    @Override
    public void onCommand(List<String> args) {


        mc.player.setPos(mc.player.getX(), mc.player.getY()+Double.parseDouble(args.get(0)), mc.player.getZ());
        mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(), mc.player.getY(), mc.player.getZ(), false));
    }

}