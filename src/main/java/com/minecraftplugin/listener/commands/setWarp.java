package com.minecraftplugin.listener.commands;

import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class setWarp {
     private Player p;
     private Location lo;
     private String wpName;

    //Constructior
    public setWarp(Player p, Location lo, String wpname) {
        this.p = p;
        this.lo = lo;
        this.wpName = wpname;
    }

    //Main method for setwarp
    public void stWarp() {
        if (Main.getData().warpExist(wpName)) {
            Main.getData().saveData(p, lo, wpName);
        }
    }
}
