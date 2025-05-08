package org.gomadango0113.tag.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GameStartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tag_start")) {

        }
        return false;
    }

}
