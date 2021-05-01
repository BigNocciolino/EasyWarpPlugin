package com.minecraftplugin.listener.commands;

import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class warp {

    private Player p;
    private String warpName;

    public warp(Player p,  String warpName) {
        this.p = p;
        this.warpName = warpName;
    }



    //Build the location for the static warp
    public Location buildlocation() {

        World w = Bukkit.getWorld(Main.getData().returnWorld(warpName));
        Location loc = new Location(w, Main.getData().returnWarpX(this.warpName),
                Main.getData().returnwarpY(this.warpName), Main.getData().returnWarpZ(this.warpName) );

        return loc;
    }

    //warp to static warp
    public void warpYourself() {
            p.teleport(buildlocation());
    }

}
