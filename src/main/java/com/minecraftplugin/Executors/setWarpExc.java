package com.minecraftplugin.Executors;

import com.minecraftplugin.listener.commands.setWarp;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setWarpExc implements CommandExecutor {

    private final String pluginName = Main.getInstance().getConfig().getString("messagiesPrefix").replaceAll("&", "§");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            Location lo = p.getLocation();
            //Command to set a warp
            if (Boolean.parseBoolean(Main.getInstance().getConfig().getString("usePermission")) == true) {
                if (p.hasPermission("warp.warp")) {
                    setTheWarp(p, args[0], lo);
                }else {
                    sender.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.setwarp.permission").replaceAll("&", "§"));
                }
            } else {
                if (command.getName().equals("setwarp")) {
                    setTheWarp(p, args[0], lo);
                }
            }
            return true;
        }
        return false;
    }

    public void setTheWarp(Player p, String args, Location lo) {
        if (args.length() >= 1) {
            String wpName = args;
            setWarp setWarp = new setWarp(p, lo, wpName);
            if (Main.getData().warpExist(wpName)) {
                setWarp.stWarp();
                p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.setwarp.succesfullySet").replaceAll("&", "§"));
            } else {
                p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.setwarp.alredyExist").replaceAll("&", "§"));
            }
        } else {
            p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.setwarp.arg").replaceAll("&", "§"));
        }
    }


}
