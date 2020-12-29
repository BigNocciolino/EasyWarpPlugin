package com.minecraftplugin.minecraftplugin;

import com.minecraftplugin.Completer.Tabcompleter;
import com.minecraftplugin.data.data;
import com.minecraftplugin.listener.commandListener;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    public static Main plugin;
    public static data data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        //All commands
        System.out.println("The pllugin was started successfully");
        this.getCommand("setwarp").setExecutor(new commandListener());
        this.getCommand("warp").setExecutor(new commandListener());
        this.getCommand("warp").setTabCompleter(new Tabcompleter());
        this.getCommand("delwarp").setExecutor(new commandListener());
        this.getCommand("delwarp").setTabCompleter(new Tabcompleter());
        this.getCommand("warps").setExecutor(new commandListener());
        data = new data();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("The pllugin was stopped successfully");
    }

    public static data getData() {
        return data;
    }

    public static Main getInstance() {
        return plugin;
    }
}
