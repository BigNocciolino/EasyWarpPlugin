package com.minecraftplugin.listener;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandListener implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        Location lo = p.getLocation();
        String wpName;

        if (sender instanceof Player) {
            if (command.equals("setwarp")) {

            }

            if (command.equals("warp")) {

            }
        }else {
            System.out.println("Non puoi eseguire il comando da console");
        }

        return false;
    }
}
