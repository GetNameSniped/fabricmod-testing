package net.canbot.canmod.networking;

import net.canbot.canmod.CanMod;
import net.minecraft.util.Identifier;

public class ModPackets {
    public static Identifier DRINKING_ID = new Identifier(CanMod.MOD_ID, "drinking");
    public static Identifier THIRST_SYNC = new Identifier(CanMod.MOD_ID, "thirst_sync");
    public static Identifier EXAMPLE_ID = new Identifier(CanMod.MOD_ID, "example");

    public static void registerC2SPackets() {

    }

    public static void registerS2CPackets() {

    }

}
