package net.canbot.canmod.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.canbot.canmod.hax.boatfly;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_TUTORIAL = "key.canmod.category.tutorial";
    public static final String KEY_SAY_CHAT = "key.canmod.say.chat";
    static boolean boatFlyIsActive = false;
    static double speed = 2;


    public static KeyBinding boatFlyToggle;
    public static void registerKeyInputs() {
        boatfly.boatFlyStart();
    }


    public static void register() {

        boatFlyToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_SAY_CHAT,
                InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
                KEY_CATEGORY_TUTORIAL
        ));
        registerKeyInputs();

    }

}
