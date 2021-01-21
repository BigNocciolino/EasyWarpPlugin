package com.minecraftplugin.minecraftplugin;

import com.minecraftplugin.Completer.Tabcompleter;
import com.minecraftplugin.Executors.*;
import com.minecraftplugin.GUI.warpGui;
import com.minecraftplugin.data.data;
import com.minecraftplugin.data.manageMessagesYaml;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;


public final class Main extends JavaPlugin {

    public static Main plugin;
    public static data data;
    private final String pluginName = "[EasyWarp]";
    static final Logger log = Logger.getLogger("Minecraft");
    public static final String PLUGIN_NAME = "Easywarp";
    public static final String LOG_HEADER = "[" + PLUGIN_NAME + "]";

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        //All commands
        System.out.println( pluginName + " The plugin was started successfully");
        this.getCommand("setwarp").setExecutor(new setWarpExc());
        this.getCommand("warp").setExecutor(new WarpExc());
        this.getCommand("warp").setTabCompleter(new Tabcompleter());
        this.getCommand("delwarp").setExecutor(new delWarpExc());
        this.getCommand("delwarp").setTabCompleter(new Tabcompleter());
        this.getCommand("warps").setExecutor(new WarpsExc());
        this.getCommand("warpto").setExecutor(new WarpTo());
        this.getServer().getPluginManager().registerEvents(new warpGui(), plugin);
        saveDefaultConfig();
        new manageMessagesYaml();
        data = new data();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println( pluginName + " The plugin was stopped successfully");
    }

    public static FileConfiguration getmessagies() {
        manageMessagesYaml manageMessagesYaml = new manageMessagesYaml();
        return manageMessagesYaml.getFile();
    }

    public static data getData() {
        return data;
    }

    public static Main getInstance() {
        return plugin;
    }

    public void logInfo(String _message) {
        log.log(Level.SEVERE, String.format("%s %s", LOG_HEADER, _message));
    }

    public void logError(String _message) {
        log.log(Level.SEVERE, String.format("%s %s", LOG_HEADER, _message));
    }
}
