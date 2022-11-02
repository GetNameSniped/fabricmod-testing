package net.canbot.canmod.item;

import net.canbot.canmod.CanMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

import net.minecraft.util.registry.Registry;


public class ModItems {
    public static final Item KEYAXE = registerItem("keyaxe",
            new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(CanMod.MOD_ID, name), item);
    };

    public static void registerModItems() {
        CanMod.LOGGER.info("Registering mod items for" + CanMod.MOD_ID);
    }
}
