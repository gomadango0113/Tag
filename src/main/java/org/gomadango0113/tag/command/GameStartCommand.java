package org.gomadango0113.tag.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.gomadango0113.tag.manager.GameManager;
import org.gomadango0113.tag.util.ChatUtil;

public class GameStartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tag_start")) {
            int game = GameManager.startGame();
            ChatUtil.sendMessage(send, "ゲームを開始しています...");
            if (game == 1) {
                ChatUtil.sendMessage(send, "ゲームを開始することができませんでした。");
            }
        }
        return false;
    }

}
