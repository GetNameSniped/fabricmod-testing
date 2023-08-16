package net.canbot.canmod.util.command;


import net.canbot.canmod.hax.Module;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class Command {

    public static MinecraftClient mc = Module.mc;

    public String name;
    public  String commandText;

    public Command(String name, String commandText) {
        this.name = name;
        this.commandText=commandText;
    }


    public void onCommand(List<String> args) {}
}
