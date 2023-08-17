package net.canbot.canmod.mixin;

import net.canbot.canmod.hax.Modules.Hud;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    @Inject(method = "render", at = @At("RETURN"), cancellable = true)
    public void render(DrawContext context, float tickDelta, CallbackInfo info){
        Hud.draw(context, tickDelta);
    }
}