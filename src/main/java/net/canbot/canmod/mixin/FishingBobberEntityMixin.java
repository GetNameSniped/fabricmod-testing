package net.canbot.canmod.mixin;

import net.canbot.canmod.hax.ModuleManager;
import net.canbot.canmod.hax.Modules.AutoFish;
import net.canbot.canmod.hax.Modules.AutoReflect;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.FishingBobberEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {

    @Shadow private boolean caughtFish;
    public boolean caught = false;
    @Inject(method = "onTrackedDataSet", at = @At("TAIL"))
    public void onTrackedDataSet(TrackedData<?> data, CallbackInfo ci) {
        AutoFish.pubCaught = caughtFish;
        System.out.println("Tracked data set: "+caughtFish);
    }
}
