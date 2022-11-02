
package net.canbot.canmod.hax;

import net.canbot.canmod.event.KeyInputHandler;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;


public class boatfly {
    static KeyBinding boatFlyToggle = KeyInputHandler.boatFlyToggle;
static Screen myScreen = new Screen(Text.of("Hi")) {

};
    static boolean boatFlyIsActive = false;
    static double speed = 2;

    public static void boatFlyStart() {

    ClientTickEvents.END_CLIENT_TICK.register(client -> {

        if (boatFlyToggle.wasPressed()) {
            if(boatFlyIsActive){
                boatFlyIsActive=false;
                assert client.player != null;
                client.player.sendMessage(Text.of("Boatfly Deactivated!"), true);

            }else{

                boatFlyIsActive=true;
                assert client.player != null;
                client.player.sendMessage(Text.of("Boatfly Activated!"), true);
            }
        };

        if (boatFlyIsActive && client.player != null) {

            if (Objects.requireNonNull(client.player).hasVehicle()) {
                double motionX = 0;
                double motionY = 0;
                double motionZ = 0;

                if (client.options.jumpKey.isPressed()) {
                    motionY = 1;

                } else if (!client.options.jumpKey.isPressed()) {
                    Vec3d velocity = client.player.getVehicle().getVelocity();

                    motionY = -0.01;

                }
                if (client.options.leftKey.isPressed()) {
                    float yawRad = client.player.getVehicle().getYaw() * MathHelper.RADIANS_PER_DEGREE;
                    motionX = MathHelper.cos(-yawRad) * speed;
                    motionZ = MathHelper.sin(yawRad) * speed;

                }
                if (client.options.sprintKey.isPressed()) {
                    motionY = -1;


                }
                if (client.options.rightKey.isPressed()) {
                    float yawRad = client.player.getVehicle().getYaw() * MathHelper.RADIANS_PER_DEGREE;
                    motionX = (MathHelper.cos(-yawRad) * speed)*-1;
                    motionZ = (MathHelper.sin(yawRad) * speed)*-1;
                    client.player.getVehicle().setYaw(client.player.getVehicle().getYaw()+5);
                }

                if (client.options.forwardKey.isPressed()) {
                    float yawRad = client.player.getVehicle().getYaw() * MathHelper.RADIANS_PER_DEGREE;
                    motionX = MathHelper.sin(-yawRad) * speed + motionX;
                    motionZ = MathHelper.cos(yawRad) * speed + motionZ;
                }
                if (client.options.backKey.isPressed()) {
                    float yawRad = client.player.getVehicle().getYaw() * MathHelper.RADIANS_PER_DEGREE;
                    motionX = (MathHelper.sin(-yawRad) * speed)*-1 + motionX;
                    motionZ = (MathHelper.cos(yawRad) * speed)*-1 + motionZ;
                }


                client.player.getVehicle().setVelocity(motionX, motionY, motionZ);
                client.player.getVehicle().setYaw(client.player.getYaw());

            }
        }

    });
    }
}