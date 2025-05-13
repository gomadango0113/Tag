package org.gomadango0113.tag.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.gomadango0113.tag.manager.event.EventManager;
import org.gomadango0113.tag.util.ChatUtil;

public class EventCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tag_event")) {
            if (args[0].equalsIgnoreCase("custom")) {
                if (args.length == 1) {
                    ChatUtil.sendMessage(send, "メッセージが必要です。");
                }
                else {
                    EventManager.addCustomEvent(send, args[1]);
                }
            }
            else {
                EventManager.addEvent(send, EventManager.EventType.valueOf(args[0]));
            }
        }
        return false;
    }

}
