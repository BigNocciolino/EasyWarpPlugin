package com.minecraftplugin.minecraftplugin;

import com.minecraftplugin.Completer.Tabcompleter;
import com.minecraftplugin.Executors.*;
import com.minecraftplugin.Utils.warpRequest.Executor.*;
import com.minecraftplugin.data.data;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class Main extends JavaPlugin {

//TODO add the reload config option

    public static Main plugin;
    public static data data;
    private final String pluginName = this.getConfig().getString("messagiesPrefix").replaceAll("&", " ");
    static final Logger log = Logger.getLogger("Minecraft");
    public static final String PLUGIN_NAME = "Easywarp";
    public static final String LOG_HEADER = "[" + PLUGIN_NAME + "]";

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        //All commands
        System.out.println( pluginName + " The plugin was started successfully");
        //For the warp
        this.getCommand("setwarp").setExecutor(new setWarpExc());
        this.getCommand("warp").setExecutor(new WarpExc());
        this.getCommand("warp").setTabCompleter(new Tabcompleter());
        this.getCommand("delwarp").setExecutor(new delWarpExc());
        this.getCommand("delwarp").setTabCompleter(new Tabcompleter());
        this.getCommand("warps").setExecutor(new WarpsExc());

        //For the warp request
        this.getCommand("warpto").setExecutor(new WarpTo());
        this.getCommand("warpaccept").setExecutor(new warpAccept());
        this.getCommand("warphere").setExecutor(new warpHereExecutor());
        this.getCommand("warplist").setExecutor(new warpList());
        this.getCommand("warpno").setExecutor(new warpNo());
        this.getCommand("warpremove").setExecutor(new warpRemove());
        this.saveDefaultConfig();
        this.reloadConfig();
        data = new data();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println( pluginName + " The plugin was stopped successfully");
    }

    public static data getData() {
        return data;
    }

    public static Main getInstance() {
        return plugin;
    }

}
