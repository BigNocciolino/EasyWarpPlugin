package com.minecraftplugin.listener.commands;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class warpTo {

    private final String pluginName = "[EasyWarp]";
    private static List<warpTo> rewuestList = new ArrayList<>();
    //private BukkitRunnable timer;
    Player recivier;
    Player sender;

    public warpTo(Player sender, Player recivier) {
        this.recivier = recivier;
        this.sender = sender;
    }

    public void warpTo() {
        sender.teleport(recivier.getLocation());
    }

}
