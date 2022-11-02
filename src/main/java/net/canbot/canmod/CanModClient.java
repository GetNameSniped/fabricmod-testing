package net.canbot.canmod;

import net.canbot.canmod.event.KeyInputHandler;
import net.canbot.canmod.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CanModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        KeyInputHandler.register();
    }
}
