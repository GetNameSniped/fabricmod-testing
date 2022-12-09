
package net.canbot.canmod.hax.Modules;

import net.canbot.canmod.event.KeyInputHandler;
import net.canbot.canmod.hax.Module;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import org.lwjgl.glfw.GLFW;

import java.util.Objects;


public class boatfly extends Module {
    static KeyBinding boatFlyToggle = KeyInputHandler.registerNewBind("CanMod", "BoatFly", GLFW.GLFW_KEY_R);
    public boatfly() {
        super("BoatFly", boatFlyToggle, Category.MOVEMENT);
    }




    static boolean boatFlyIsActive = false;
    static double speed = 2;


    @Override

    public void onTick() {
        if (this.isToggled() && mc.player != null) {

            if (Objects.requireNonNull(mc.player).hasVehicle()) {
                double motionX = 0;
                double motionY = 0;
                double motionZ = 0;

                if (mc.options.jumpKey.isPressed()) {
                    motionY = 1;

                } else if (!mc.options.jumpKey.isPressed()) {
                    Vec3d velocity = mc.player.getVehicle().getVelocity();

                    motionY = -0.01;

                }
                if (mc.options.leftKey.isPressed()) {
                    float yawRad = mc.player.getVehicle().getYaw() * MathHelper.RADIANS_PER_DEGREE;
                    motionX = MathHelper.cos(-yawRad) * speed;
                    motionZ = MathHelper.sin(yawRad) * speed;

                }
                if (mc.options.sprintKey.isPressed()) {
                    motionY = -1;


                }
                if (mc.options.rightKey.isPressed()) {
                    float yawRad = mc.player.getVehicle().getYaw() * MathHelper.RADIANS_PER_DEGREE;
                    motionX = (MathHelper.cos(-yawRad) * speed)*-1;
                    motionZ = (MathHelper.sin(yawRad) * speed)*-1;
                    mc.player.getVehicle().setYaw(mc.player.getVehicle().getYaw()+5);
                }

                if (mc.options.forwardKey.isPressed()) {
                    float yawRad = mc.player.getVehicle().getYaw() * MathHelper.RADIANS_PER_DEGREE;
                    motionX = MathHelper.sin(-yawRad) * speed + motionX;
                    motionZ = MathHelper.cos(yawRad) * speed + motionZ;
                }
                if (mc.options.backKey.isPressed()) {
                    float yawRad = mc.player.getVehicle().getYaw() * MathHelper.RADIANS_PER_DEGREE;
                    motionX = (MathHelper.sin(-yawRad) * speed)*-1 + motionX;
                    motionZ = (MathHelper.cos(yawRad) * speed)*-1 + motionZ;
                }


                mc.player.getVehicle().setVelocity(motionX, motionY, motionZ);
                mc.player.getVehicle().setYaw(mc.player.getYaw());

            }
        }
    }


}