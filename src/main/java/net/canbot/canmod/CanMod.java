package net.canbot.canmod;

//import net.canbot.canmod.item.ModItems;
import net.canbot.canmod.hax.Module;
import net.canbot.canmod.hax.ModuleManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

public class CanMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "canmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final CanMod INSTANCE = new CanMod();
	public ModuleManager moduleManager;
	public static List<Module> sortedModules;
	public static String NAME = "CanMod";
	public static String VERSION = "0.0.1";
	public MinecraftClient mc;
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.

		LOGGER.info("Hello Fabric world!");
		mc = MinecraftClient.getInstance();
		moduleManager = new ModuleManager();
		//ModItems.registerModItems();
		sortedModules = ModuleManager.getModules();
		sortedModules.sort(Comparator.comparingInt((Module module) -> module.getName().length()).reversed());

	}
	public static double playerX = 0;
	public static double playerZ = 0;
	public void onTick() {
		if(nullCheck()){return;}
		for(Module m : ModuleManager.getModules()){
			if(m.isToggled()) {
				m.onTick();

			}
		}
		for (Module m : ModuleManager.getModules()) {
			if (m.bind.wasPressed()) {
				m.toggle();
				LOGGER.info("a bound bind was pressed");
			}
		}

}

	public boolean nullCheck() {
		return  MinecraftClient.getInstance() == null || MinecraftClient.getInstance().world == null; // Borrowed from l4j cos good idea :)

	}

	public void onPacketSend(Packet<?> packet) {
		for(Module m : ModuleManager.getModules()){
			if(m.isToggled()) {
				m.onPacketSend(packet);
			}
		}
	}
	public void onPacketReceive(Packet<?> packet) {
		for(Module m : ModuleManager.getModules()){
			if(m.isToggled()) {
				m.onPacketReceive(packet);
			}
		}
	}
}
