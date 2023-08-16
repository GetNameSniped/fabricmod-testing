package net.canbot.canmod.mixin;


import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.canbot.canmod.screens.CanmodMainScreen;
import net.canbot.canmod.screens.ConfirmScreen;
import net.minecraft.client.gui.screen.DialogScreen;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.canbot.canmod.hax.Module.mc;


@Mixin(GameMenuScreen.class)
public abstract class MixinScreen extends Screen {

    protected MixinScreen(Text text) {super(text);}
    @Inject(at=@At("HEAD"), method = "initWidgets")
    protected void initWidgets(CallbackInfo ci) {

        this.addDrawableChild(
                ButtonWidget.builder(Text.of("CanMod Menu"), b -> openMenu())
                .dimensions(0, 0, 70, 20)
                .tooltip(Tooltip.of(Text.of("Open The CanMod Menu")))
                        .build()
        );




    }
    BooleanConsumer callback = b -> {
        if (b) {
            mc.setScreen(new MessageScreen(Text.of("Correct!!!!")));
        } else {
            mc.setScreen(new MessageScreen(Text.of("Wrongggg!")));
        }
    };
    private void openMenu() {
        mc.setScreen(new CanmodMainScreen());
    }
}