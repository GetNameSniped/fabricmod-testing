package net.canbot.canmod.hax.Modules;

import net.canbot.canmod.hax.Module;
import org.lwjgl.glfw.GLFW;

public class IamBot extends Module {
    public IamBot() {
        super("IamBot", GLFW.GLFW_KEY_V, Category.MOVEMENT);
    }
    public static double roundCoordinate(double n) {
        n = Math.round(n * 100) / 100d;  // Round to 1/100th
        return Math.nextAfter(n, n + Math.signum(n));  // Fix floating point errors
    }
    public static boolean enabled = true;
    public static boolean isEnabled() {
        return enabled;

    }
    public static void toggleIam() {
        enabled = !enabled;
    }
}
