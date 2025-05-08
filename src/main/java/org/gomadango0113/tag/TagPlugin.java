package org.gomadango0113.tag;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.gomadango0113.tag.command.GameStartCommand;

public final class TagPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommand();
        registerListener();

        getLogger().info("プラグインが有効になりました。");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommand() {
        getCommand("tag_start").setExecutor(new GameStartCommand());
    }

    private void registerListener() {
        PluginManager plm = getServer().getPluginManager();
    }

    public static TagPlugin getInstance() {
        return getPlugin(TagPlugin.class);
    }

}
