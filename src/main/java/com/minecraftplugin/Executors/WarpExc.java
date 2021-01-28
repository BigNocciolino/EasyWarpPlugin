package com.minecraftplugin.Executors;

import com.minecraftplugin.listener.commands.warp;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpExc implements CommandExecutor {

    private final String pluginName = Main.getInstance().getConfig().getString("messagiesPrefix").replaceAll("&", "§");

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
                        p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warp.arg").replaceAll("&", "§"));
                    }
                }else {
                    sender.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warp.permission").replaceAll("&", "§"));
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
                p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warp.warped").replaceAll("&", "§"));
            } else {
                p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warp.notExist").replaceAll("&", "§"));
            }
        } else {
            p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warp.arg").replaceAll("&", "§"));
        }
    }
}
