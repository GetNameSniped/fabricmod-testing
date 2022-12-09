package net.canbot.canmod.mixin;

import net.canbot.canmod.hax.ModuleManager;
import net.canbot.canmod.CanMod;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.s2c.play.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
                }
            }
        }
    }
}