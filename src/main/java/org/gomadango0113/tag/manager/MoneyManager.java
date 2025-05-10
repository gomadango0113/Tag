package org.gomadango0113.tag.manager;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.gomadango0113.tag.TagPlugin;

import java.util.HashMap;
import java.util.Map;

public class MoneyManager {

    private static final int MONEY_ADD_YEN = 100;
    private static final int MONEY_ADD_TIME = 1;

    private static final Map<OfflinePlayer, Integer> money_add_map = new HashMap<>();
    private static final Map<OfflinePlayer, Integer> player_money_map = new HashMap<>();

    public static void startMoneyAdd() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!money_add_map.containsKey(player)) {
                        money_add_map.put(player, MONEY_ADD_YEN);
                    }

                    if (!player_money_map.containsKey(player)) {
                        player_money_map.put(player, 0);
                    }

                    if (TeamManager.getPlayerTeam(player) == TeamManager.GameTeam.RUN) {
                        Integer add_money = money_add_map.get(player);
                        Integer now_money = player_money_map.get(player);

                        player_money_map.put(player, add_money + now_money);
                    }
                }
            }
        }.runTaskTimer(TagPlugin.getInstance(), 0L, 20L*MONEY_ADD_TIME);
    }

    public static Integer getNowMoney(OfflinePlayer player) {
        return player_money_map.get(player);
    }

    public static Integer getAddMoney(OfflinePlayer player) {
        return money_add_map.get(player);
    }

}
