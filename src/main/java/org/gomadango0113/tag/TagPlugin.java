package org.gomadango0113.tag;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.gomadango0113.tag.command.EventCommand;
import org.gomadango0113.tag.command.GameStartCommand;
import org.gomadango0113.tag.listener.PlayerDamageListener;
import org.gomadango0113.tag.listener.PlayerInteractListener;
import org.gomadango0113.tag.manager.ScoreboardManager;

public final class TagPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommand();
        registerListener();
        ScoreboardManager.setScoreboard(0);

        getLogger().info("プラグインが有効になりました。");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommand() {
        getCommand("tag_start").setExecutor(new GameStartCommand());
        getCommand("tag_event").setExecutor(new EventCommand());
    }

    private void registerListener() {
        PluginManager plm = getServer().getPluginManager();

        plm.registerEvents(new PlayerDamageListener(), this);
        plm.registerEvents(new PlayerInteractListener(), this);
    }

    public static TagPlugin getInstance() {
        return getPlugin(TagPlugin.class);
    }

}
