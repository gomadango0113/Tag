package org.gomadango0113.tag.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ChatUtil {

    private static final String TEXT_INFO = ChatColor.AQUA + "[鬼ごっこ] " + ChatColor.RESET;
    private static final String TEXT_ERROR = ChatColor.RED + "[エラー] " + ChatColor.RESET;

    public static void sendMessage(CommandSender send, String message) {
        send.sendMessage(TEXT_INFO + message);
    }

    public static void sendGlobalMessage(String message) {
        Bukkit.broadcastMessage(TEXT_INFO + message);
    }

}
