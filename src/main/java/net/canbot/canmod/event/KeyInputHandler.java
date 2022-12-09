package net.canbot.canmod.event;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.canbot.canmod.hax.Modules.boatfly;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY = "Hello";
    public static final String KEYNAME = "Hi";
    static boolean boatFlyIsActive = false;
    static double speed = 2;


    public static KeyBinding boatFlyToggle;
    public static void registerKeyInputs() {
        System.out.println("Registered Keys");
    }


    public static void register() {
        registerKeyInputs();


    }

    public static KeyBinding registerNewBind(String category, String NAME, int DefaultKey) {
        KeyBinding binding = KeyBindingHelper.registerKeyBinding(new KeyBinding(NAME, InputUtil.Type.KEYSYM, DefaultKey, category ));
        return binding;
    }
}
