package net.canbot.canmod.event;

import net.canbot.canmod.hax.ModuleManager;

import java.util.Objects;

public class MovementUtil {
    public static double botAdjustCoord(double i) {
        if(false==true) return i;
        double temp = (double) (Math.round(i*100))/100;
        return (double) Math.nextAfter(temp, temp + Math.signum(i));
    }
}
