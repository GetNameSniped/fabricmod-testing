package net.canbot.canmod.util.command.commands;

import net.canbot.canmod.util.command.Command;
import net.canbot.canmod.util.misc.Strings;
import net.minecraft.text.Text;

import java.util.List;

public class SayCommand extends Command {



    public SayCommand() {
        super("SayCommand", "say");
    }

    @Override
    public void onCommand(List<String> args) {

        mc.player.sendMessage(Text.of(Strings.fromStringList(args)));


    }
}
