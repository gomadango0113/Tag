package org.gomadango0113.tag.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.gomadango0113.tag.util.ChatUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventManager {

    private static final LinkedList<TagEvent> event_queue = new LinkedList<>();

    public static void addCustomEvent(CommandSender sender, String message) {
        event_queue.add(new TagEvent(sender, message));

        for (Player online : Bukkit.getOnlinePlayers()) {
            PlayerInventory online_inv = online.getInventory();
            if (!online_inv.contains(Material.WRITTEN_BOOK)) {
                online_inv.addItem(getRawEventBook());
            }

            for (ItemStack content : online_inv.getContents()) {
                if (content != null) {
                    if (content.getType() == Material.WRITTEN_BOOK) {
                        Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 50, 1));
                        BookMeta book_meta = (BookMeta) content.getItemMeta();
                        book_meta.setAuthor("運営");
                        book_meta.setTitle("イベントブック");
                        book_meta.addPage(
                                "【イベント】" + "\n" +
                                        message);
                        content.setItemMeta(book_meta);
                    }
                }
            }
        }
        ChatUtil.sendGlobalMessage("イベントが発動されました。本をご覧ください。");
    }

    public static LinkedList<TagEvent> getEventList() {
        return event_queue;
    }

    public static ItemStack getRawEventBook() {
        ItemStack itemstack = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta meta = itemstack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "イベントブック");
        meta.setLore(Arrays.asList("イベントを表示する本", "--------"));
        itemstack.setItemMeta(meta);
        
        return itemstack;
    }

    public static class TagEvent {
        private final CommandSender sender;
        private final boolean custom;
        private boolean ending;
        private final String message;

        public TagEvent(CommandSender sender, String message) {
            this.sender = sender;
            this.message = message;
            this.custom = true;
            this.ending = false;
        }

        public CommandSender getSender() {
            return sender;
        }

        public boolean isCustom() {
            return custom;
        }

        public boolean isEnding() {
            return ending;
        }

        public void setEnding(boolean flag) {
            this.ending = flag;
        }

        public String getMessage() {
            return message;
        }
    }
}
