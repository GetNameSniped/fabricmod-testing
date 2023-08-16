package net.canbot.canmod.mixin;

import net.canbot.canmod.hax.ModuleManager;
import net.canbot.canmod.CanMod;
import net.canbot.canmod.util.command.CommandHandler;
import net.minecraft.network.ClientConnection;

import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

import static net.canbot.canmod.hax.Module.mc;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {


    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void onHandlePacket(Packet<T> packet, PacketListener listener, CallbackInfo ci) {
        CanMod.INSTANCE.onPacketReceive(packet);


        if(ModuleManager.getModulebyName("NoBorder") != null) {
            if (ModuleManager.getModulebyName("NoBorder").isToggled()) {
                if ((packet instanceof WorldBorderCenterChangedS2CPacket || packet instanceof WorldBorderSizeChangedS2CPacket
                        || packet instanceof WorldBorderInitializeS2CPacket
                        || packet instanceof WorldBorderInterpolateSizeS2CPacket
                        || packet instanceof WorldBorderWarningBlocksChangedS2CPacket)) {
                    ci.cancel();
                } else if (packet instanceof GameStateChangeS2CPacket && ((GameStateChangeS2CPacket) packet).getReason()==GameStateChangeS2CPacket.GAME_MODE_CHANGED||packet instanceof GameStateChangeS2CPacket && ((GameStateChangeS2CPacket) packet).getReason()==GameStateChangeS2CPacket.DEMO_MESSAGE_SHOWN) {
                    ci.cancel();
                }
            }
        }
    }

    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/PacketCallbacks;)V", at = @At("HEAD"), cancellable = true)
    private void send(Packet<?> packet, PacketCallbacks packetCallback, CallbackInfo callback) {
        if (packet instanceof ChatMessageC2SPacket) {
            mc.player.sendMessage(Text.of(String.valueOf(((ChatMessageC2SPacket) packet).chatMessage().startsWith(";"))), true);
            //System.out.println();

            if (String.valueOf(((ChatMessageC2SPacket) packet).chatMessage().startsWith(CommandHandler.prefix)).equals("true")) {
                String message = ((ChatMessageC2SPacket) packet).chatMessage();
                String[] command = message.replaceFirst(CommandHandler.prefix, "").split(" ", 2);
                List<String> args = new ArrayList<String>();
                int i = 0;
                for (String item:message.split(" ", 21)) {
                    if (i!=0) {
                        args.add(item);
                    }
                    i=1;
                }

                callback.cancel();
                CommandHandler.handleCommand(command, args);
            }
        }
    }
}