package net.canbot.canmod.mixin;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

import net.canbot.canmod.hax.Modules.IamBot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerMoveC2SPacket.class})
public class MixinMovementPacket {
    @Shadow
    @Mutable
    @Final
    protected double x;

    @Shadow
    @Mutable
    @Final
    protected double y;

    @Shadow
    @Mutable
    @Final
    protected double z;
    @Shadow @Mutable @Final boolean onGround;



    @Inject(at = @At("RETURN"), method = "<init>*")
    public void onMovePacket(double x, double y, double z, float yaw, float pitch, boolean onGround, boolean changePosition, boolean changeLook, CallbackInfo ci) {


        if (IamBot.isEnabled()) {
            this.x=IamBot.roundCoordinate(x);
            this.z=IamBot.roundCoordinate(z);
            //this.x = (Math.round(((long) x * 100) / 100.0));
            //this.y = (((long) y+1));
            //this.z = (Math.round(((long) z * 100) / 100.0));
            System.out.println(this.z);
            this.onGround=true;
        }
    }

}

/*
@Mixin(PlayerMoveC2SPacket.PositionAndOnGround.class)
public class MixinMovementPacket {
    // Anti-human bypass
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V"))
    private static void init(Args args) {
        RoundPosition.onPositionPacket(args);
    }

}*/