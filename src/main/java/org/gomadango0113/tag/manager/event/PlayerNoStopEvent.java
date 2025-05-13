package org.gomadango0113.tag.manager.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.gomadango0113.tag.TagPlugin;
import org.gomadango0113.tag.manager.TeamManager;
import org.gomadango0113.tag.util.ChatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlayerNoStopEvent implements EventManager.EventInterface, Listener {

    private static final Map<UUID, Integer> map = new HashMap<>();
    private static final int NO_STOP_TIME = 10;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (from.getBlockX() != to.getBlockX()
                || from.getBlockY() != to.getBlockY()
                || from.getBlockZ() != to.getBlockZ()) {
            map.put(player.getUniqueId(), NO_STOP_TIME);
        }
    }

    @Override
    public void addEvent() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (!map.containsKey(online.getUniqueId())) {
                        map.put(online.getUniqueId(), NO_STOP_TIME);
                    }

                    Integer now_time = map.get(online.getUniqueId());
                    if (now_time == 0) {
                        Set<Player> oni_player_list = TeamManager.getTeamPlayer(TeamManager.GameTeam.ONI);
                        for (Player oni : oni_player_list) {
                            ChatUtil.sendMessage(oni, online.getName() + "の座標は" + online.getLocation() + "です。");
                        }
                        map.put(online.getUniqueId(), NO_STOP_TIME);
                    }
                    else {
                        map.put(online.getUniqueId(), now_time - 1);
                        online.sendTitle(String.valueOf(now_time), null);
                    }
                }
            }
        }.runTaskTimer(TagPlugin.getInstance(), 0L, 20L);
    }

    @Override
    public void stopEvent() {

    }

    @Override
    public EventManager.EventType getType() {
        return EventManager.EventType.PLAYER_NO_STOP_EVENT;
    }
}
