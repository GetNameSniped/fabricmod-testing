package net.canbot.canmod.util.command;


import net.canbot.canmod.CanMod;
import net.canbot.canmod.util.command.commands.*;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    public static String prefix = ";";
    public static List<Command> commands = new ArrayList<>();

    public CommandHandler()  {
        System.out.println("Handler initiated.");
        commands.add(new SayCommand());
        commands.add(new VclipCommand());
        commands.add(new BadVclipCommand());
    }


    public static void handleCommand(String[] command, List<String> args) {

        for (Command selCommand: commands) {

            if (selCommand.commandText.equals(command[0])) {
                if (!CanMod.INSTANCE.nullCheck()) {
                    selCommand.onCommand(args);
                    return;
                }
            }

        }
        
        Command.mc.player.sendMessage(Text.of("Unknown Command! Use \""+ prefix + "help\" for a list of commands."));
    }
}
