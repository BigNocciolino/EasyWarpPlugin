package com.minecraftplugin.listener;

import com.minecraftplugin.listener.commands.delWarp;
import com.minecraftplugin.listener.commands.setWarp;
import com.minecraftplugin.listener.commands.warp;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandListener implements CommandExecutor {

    String pluginName = "§5[EasyWarp]§5";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        Location lo = p.getLocation();
        String wpName = args[0];

        if (sender instanceof Player) {
            if (command.getName().equals("setwarp")) {
                setWarp setWarp = new setWarp(p, lo, wpName);
                if (Main.getData().warpExist(wpName)) {
                    setWarp.stWarp();
                    sender.sendMessage(pluginName + "§6 Succesfully set the warp §6");
                }else {
                    sender.sendMessage("§6Il warp esiste§6");
                }
            }

            if (command.getName().equals("warp")) {
                warp warp = new warp(p, wpName);
                warp.warpYourself();
                sender.sendMessage(pluginName + "§6 You warped yourself §6");
            }

            if (command.getName().equals("delwarp")) {
                delWarp delWarp = new delWarp(wpName);
                delWarp.delwarp();
            }
        }else {
            System.out.println("§6Non puoi eseguire il comando da console§6");
        }

        return false;
    }
}
