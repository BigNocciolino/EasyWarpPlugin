package com.minecraftplugin.Executors;

import com.minecraftplugin.Utils.CustomMessagies;
import com.minecraftplugin.listener.commands.warp;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpExc implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Command for warp
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Boolean.parseBoolean(Main.getInstance().getConfig().getString("usePermission")) == true) {
                if (p.hasPermission("warp.warp")) {
                    if (args.length >= 1) {
                        warpYou(p, args[0]);
                    }else {
                        CustomMessagies.sendMessage(p, "warp.arg");
                    }
                }else {
                    CustomMessagies.sendMessage(p, "warp.permission");
                }
            } else {
                if (command.getName().equals("warp")) {
                    warpYou(p, args[0]);
                }

            }
            return true;
        }
        return false;
    }


    public void warpYou(Player p, String args) {
        if (args.length() >= 1) {
            String wpName = args;
            warp warp = new warp(p, wpName);
            if (Main.getData().warpExist(wpName) != true) {
                warp.warpYourself();
                CustomMessagies.sendMessage(p, "warp.warped", "{warp}", wpName);
            } else {
                CustomMessagies.sendMessage(p, "warp.notExist");
            }
        } else {
            CustomMessagies.sendMessage(p, "warp.arg");
        }
    }
}
