package net.canbot.canmod.util.render;

import net.canbot.canmod.mixin.AccessorFrustum;
import net.canbot.canmod.mixin.AccessorWorldRenderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;


public class FrustumUtils {

	public static Frustum getFrustum() {
		return ((AccessorWorldRenderer) MinecraftClient.getInstance().worldRenderer).getFrustum();
	}

	public static boolean isBoxVisible(Box box) {
		return getFrustum().isVisible(box);
	}

	public static boolean isPointVisible(Vec3d vec) {
		return isPointVisible(vec.x, vec.y, vec.z);
	}

	public static boolean isPointVisible(double x, double y, double z) {
		AccessorFrustum frustum = (AccessorFrustum) getFrustum();
		return frustum.getFrustumIntersection().testPoint((float) (x - frustum.getX()), (float) (y - frustum.getY()), (float) (z - frustum.getZ()));
	}
}