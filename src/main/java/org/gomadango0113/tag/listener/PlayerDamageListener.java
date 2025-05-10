package org.gomadango0113.tag.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.gomadango0113.tag.manager.TeamManager;
import org.gomadango0113.tag.util.ChatUtil;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onByDamage(EntityDamageByEntityEvent event) {
        Entity damage = event.getEntity(); //ダメージを受けた人
        Entity damager = event.getDamager(); //ダメージを与えた人

        if (damage instanceof Player && damager instanceof Player) {
            Player damage_player = (Player) damage;
            Player damager_player = (Player) damager;
            //ダメージを受けた人：逃走者、与えた人：鬼
            if (TeamManager.getPlayerTeam(damage_player) == TeamManager.GameTeam.RUN &&
                    TeamManager.getPlayerTeam(damager_player) == TeamManager.GameTeam.ONI) {
                TeamManager.getTeam(TeamManager.GameTeam.RUN).removeEntry(damage_player.getName());
                TeamManager.getTeam(TeamManager.GameTeam.PRISONER).addEntry(damage_player.getName());
                ChatUtil.sendMessage(damage_player, "あなたは鬼に捕まってしまいました。");
                event.setDamage(0);
            }
            else {
                event.setCancelled(true);
            }
        }
    }

}
