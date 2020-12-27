package com.minecraftplugin.minecraftplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        System.out.println("The pllugin was started successfully");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("The pllugin was stopped successfully");
    }

    public static Main getInstance() {
        return plugin;
    }
}
