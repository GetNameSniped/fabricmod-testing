package net.canbot.canmod.hax;


import net.canbot.canmod.event.KeyInputHandler;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.network.Packet;
import net.minecraft.text.Text;

public class Module {
    public static MinecraftClient mc = MinecraftClient.getInstance();
    public String name;
    public Category category;
    public KeyBinding bind;
    public boolean toggled;

    public Module(String name, KeyBinding bind, Category category) {
        this.name = name;
        this.bind=bind;
        this.category = category;

    }
    public String getName() {
        return name;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void onPressed() {

    }

    public void onTick(){}

    public void isEnabled(KeyBinding bind) {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(bind.wasPressed()) {
                this.toggle();
                System.out.println("bind was pressed");
            }
        });
    }



    public void onPacketSend(Packet<?> packet){}

    public void onPacketReceive(Packet<?> packet){}

    public void onEnable(){}

    public void onDisable(){}

    public boolean showModule = true;
    public void toggleShown() {
        showModule=!showModule;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public void toggle() {
        this.toggled = !toggled;

        if(toggled){
            onEnable();
        }else {
            onDisable();
        }

    }
    int bindInt;


    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public KeyBinding getBind() {
        return bind;
    }

    public void setBind(String category, String name, int binding) {
        bind = KeyInputHandler.registerNewBind(category, name, binding);
        bindInt=binding;
        name=name;
        category=category;
    }

    public boolean nullCheck() {
        return mc.player == null || mc.world == null; // Borrowed from l4j cos good idea :)
        //https://github.com/Logging4J/AutoLog.CC/blob/master/src/main/java/cc/l4j/autolog/AutoLog.java
    }
    public static void sendMessage(String msg){
        mc.player.sendMessage(Text.of(msg), false);
    }

    public int getBindInt() {
        return KeyBindingHelper.getBoundKeyOf(bind).getCode();

    }

    public enum Category{
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        EXPLOIT("Exploit"),
        RENDER("Render");

        public final String name;

        Category(String name){
            this.name = name;
        }
        public boolean nullCheck() {
            return mc.player == null || mc.world == null; // Borrowed from l4j cos good idea :)
            //https://github.com/Logging4J/AutoLog.CC/blob/master/src/main/java/cc/l4j/autolog/AutoLog.java
        }
    }
}