package net.canbot.canmod.hax.Modules;


import net.canbot.canmod.event.KeyInputHandler;
import net.canbot.canmod.hax.Module;

public class NoBorder extends Module {
    public NoBorder() {
        super("NoBorder", KeyInputHandler.registerNewBind("Misc", "NoBorder", -1), Category.RENDER);
        this.toggle();
        this.showModule=false;
    }

}
