package net.canbot.canmod.mixin;


import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(GameMenuScreen.class)
public abstract class MixinScreen extends Screen {
    protected MixinScreen(Text text) {super(text);}
    @Inject(at=@At("HEAD"), method = "initWidgets")
    protected void initWidgets(CallbackInfo ci) {
        this.addDrawableChild(new ButtonWidget(this.width/2-180, this.height/2+35-145, 50, 20, Text.of("CanMod Menu"), (button) -> {

        }));

    }
}