package net.canbot.canmod.mixin;

/*import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

import net.canbot.canmod.hax.IamBot;
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
    static double roundCoordinate(double n) {
        n = Math.round(n * 100) / 100d;  // Round to 1/100th
        return Math.nextAfter(n, n + Math.signum(n));  // Fix floating point errors
    }

//shush idk how to use mixins
    //im dumb asf when it comes to mixins lol
    //i just look how other ppl use them
    //im quite new to fabric modding anyways
    //i only know java cuz its similar to js
//95% of ppl know how to do math, are you that 2%
    //yes i know how to multiline comment i just cbf rn
    @Inject(at = @At("RETURN"), method = "<init>*")
    public void onMovePacket(double x, double y, double z, float yaw, float pitch, boolean onGround, boolean changePosition, boolean changeLook, CallbackInfo ci) {


        if (IamBot.isEnabled()) {
            this.x=roundCoordinate(x);
            this.z=roundCoordinate(z);
            //this.x = (Math.round(((long) x * 100) / 100.0));
            //this.y = (((long) y+1));
            //this.z = (Math.round(((long) z * 100) / 100.0));
            System.out.println(this.z);
        }
    }

}
*/
import net.canbot.canmod.util.RoundPosition;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PlayerMoveC2SPacket.PositionAndOnGround.class)
public class MixinMovementPacket {
    // Anti-human bypass
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V"))
    private static void init(Args args) {
        RoundPosition.onPositionPacket(args);
    }

}