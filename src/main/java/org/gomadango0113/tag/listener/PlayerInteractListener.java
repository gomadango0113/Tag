package org.gomadango0113.tag.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.gomadango0113.tag.util.ChatUtil;

import java.util.Arrays;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerInventory p_inv = player.getInventory();
        Action action = event.getAction();
        Block click_block = event.getClickedBlock();
        Location loc = click_block.getLocation();

        if (action == Action.RIGHT_CLICK_BLOCK) {
            BlockData data = click_block.getBlockData();
            if (data instanceof Directional) {
                Directional directional = (Directional) data;
                BlockFace face = directional.getFacing();
                Block place_block = click_block.getRelative(face.getOppositeFace());
                if (click_block.getType() == Material.OAK_BUTTON && place_block.getType() == Material.DIAMOND_BLOCK) {
                    if (p_inv.contains(getDiamond())) {
                        ChatUtil.sendMessage(player, "すでに逃走成功に必要なダイアモンドを持っています。");
                    }
                    else {
                        p_inv.addItem(getDiamond());
                        ChatUtil.sendMessage(player, "逃走成功に必要なダイヤモンドをゲットしました。");
                    }
                }
            }
        }
    }

    private static ItemStack getDiamond() {
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "ダイアモンド");
        meta.setLore(Arrays.asList(ChatColor.GOLD + "他の人に渡すことはできません。Qキーで捨てると消えます。"));
        item.setItemMeta(meta);

        return item;
    }

}
