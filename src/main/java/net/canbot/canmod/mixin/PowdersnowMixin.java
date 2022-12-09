package net.canbot.canmod.mixin;

import net.canbot.canmod.CanMod;


import net.canbot.canmod.hax.Module;
import net.canbot.canmod.hax.ModuleManager;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.canbot.canmod.hax.Modules.PowderFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(PowderSnowBlock.class)
public class PowdersnowMixin {


    private static MinecraftClient mclient = CanMod.INSTANCE.mc;

    @Inject(method = "canWalkOnPowderSnow", at = @At("HEAD"), cancellable = true)
    private static void onCanWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(PowderFloat.powderMixin(entity));

    }
}