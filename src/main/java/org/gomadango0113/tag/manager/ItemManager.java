package org.gomadango0113.tag.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.gomadango0113.tag.util.ItemUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class ItemManager {

    public static ItemStack getTeamArmor(TeamManager.GameTeam team, EquipmentSlot slot) {
        if (team == TeamManager.GameTeam.ONI) {
            HashMap<EquipmentSlot, ItemStack> item_map = new HashMap<>();

            ItemStack helmet = new ItemUtil(Material.DIAMOND_HELMET)
                    .addEnc(Enchantment.BINDING_CURSE, 1)
                    .getItemStack("ヘルメット", Collections.singletonList(ChatColor.GOLD + "鬼用のヘルメット"));
            ItemStack ches = new ItemUtil(Material.DIAMOND_CHESTPLATE)
                    .addEnc(Enchantment.BINDING_CURSE, 1)
                    .getItemStack("チェストプレート", Collections.singletonList(ChatColor.GOLD + "鬼用のチェストプレート"));
            ItemStack leg = new ItemUtil(Material.DIAMOND_LEGGINGS)
                    .addEnc(Enchantment.BINDING_CURSE, 1)
                    .getItemStack("レギンス", Collections.singletonList(ChatColor.GOLD + "鬼用のレギンス"));
            ItemStack boots = new ItemUtil(Material.DIAMOND_BOOTS)
                    .addEnc(Enchantment.BINDING_CURSE, 1)
                    .getItemStack("ブーツ", Collections.singletonList(ChatColor.GOLD + "鬼用のブーツ"));

            item_map.put(EquipmentSlot.HEAD, helmet);
            item_map.put(EquipmentSlot.CHEST, ches);
            item_map.put(EquipmentSlot.LEGS, leg);
            item_map.put(EquipmentSlot.FEET, boots);

            return item_map.get(slot);
        }

        return null;
    }

    public static ItemStack[] getTeamArmorArray(TeamManager.GameTeam team) {
        if (team == TeamManager.GameTeam.ONI) {
            return Arrays.asList(
                    getTeamArmor(team, EquipmentSlot.FEET),
                    getTeamArmor(team, EquipmentSlot.LEGS),
                    getTeamArmor(team, EquipmentSlot.CHEST),
                    getTeamArmor(team, EquipmentSlot.HEAD)
            ).toArray(new ItemStack[0]);
        }
        return null;
    }
}
