package net.canbot.canmod.util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.math.BigDecimal;
import java.math.MathContext;

public class RoundPosition {
    static MathContext precision = new MathContext(3);
    static MinecraftClient client = MinecraftClient.getInstance();
    public static BigDecimal roundCoordinate(double n) {

          //  n = Math.round(n * 100) / 100d;  // Round to 1/100th
           // return Math.round(n * 100) / 100d;// Math.nextAfter(n, n + Math.signum(n));  // Fix floating point errors
        return BigDecimal.valueOf(n+1).round(precision).subtract(BigDecimal.valueOf(1));


    }


    public static void onPositionPacket(Args args) {
        double xa = args.get(0);
        double ya = args.get(1);
        double za = args.get(2);


        var x = roundCoordinate(args.get(0));
        var z = roundCoordinate(args.get(2));
        // Round to 100ths for "Anti-Human" check

        //y = roundCoordinate(y);

        args.set(0, x.doubleValue());
        //args.set(1, y);
        args.set(2, z.doubleValue());

        assert client.player != null;
        client.player.sendMessage(Text.of("X:"+x+"Z:"+z+"        Zeros:"+(z.doubleValue()*1000)%10), true);
    }
}
