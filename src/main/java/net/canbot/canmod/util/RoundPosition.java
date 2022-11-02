package net.canbot.canmod.util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
public class RoundPosition {
    static MinecraftClient client = MinecraftClient.getInstance();
    public static double roundCoordinate(double n) {
        if (n<0) {
            n*=-1;
            n = Math.round(n * 100) / 100d;  // Round to 1/100th
            return (Math.round(n*100)/100d)*-1;// Math.nextAfter(n, n + Math.signum(n));  // Fix floating point errors
        } else {
            n = Math.round(n * 100) / 100d;  // Round to 1/100th
            return Math.round(n * 100) / 100d;// Math.nextAfter(n, n + Math.signum(n));  // Fix floating point errors
        }
    }

    public static void onPositionPacket(Args args) {
        double x = args.get(0);
        double y = args.get(1);
        double z = args.get(2);

        // Round to 100ths for "Anti-Human" check
        x = roundCoordinate(x);
        z = roundCoordinate(z);
        y = roundCoordinate(y);

        args.set(0, x);
        args.set(1, y);
        args.set(2, z);

        assert client.player != null;
        client.player.sendMessage(Text.of("X:"+Double.toString(x/*(roundCoordinate(x)*1000)%10*/)+"Z:"+z), true);
    }
}
