package net.canbot.canmod.mixin;

import net.canbot.canmod.CanMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


public class ExampleMixin {

	static MinecraftClient client = MinecraftClient.getInstance();
	static TextRenderer tr = client.textRenderer;

	static MatrixStack matrix = new MatrixStack();

	private void init(CallbackInfo info) {

		CanMod.LOGGER.info("This line is printed by an example mod mixin!");


	}
}
