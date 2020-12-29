package com.minecraftplugin.listener;

import com.minecraftplugin.listener.commands.delWarp;
import com.minecraftplugin.listener.commands.setWarp;
import com.minecraftplugin.listener.commands.warp;
import com.minecraftplugin.listener.commands.warplist;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class commandListener implements CommandExecutor {

    String pluginName = "§5[EasyWarp]§5";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        Location lo = p.getLocation();

        if (sender instanceof Player) {
            if (command.getName().equals("setwarp")) {
                if (args.length >= 1) {
                    String wpName = args[0];
                    setWarp setWarp = new setWarp(p, lo, wpName);
                    if (Main.getData().warpExist(wpName)) {
                        setWarp.stWarp();
                        sender.sendMessage(pluginName + "§6 Succesfully set the warp §6");
                    } else {
                        sender.sendMessage(pluginName + "§6 The warp already exists");
                    }
                }else {
                    sender.sendMessage(pluginName + "§6 The argument must be present§6");
                }
            }

            if (command.getName().equals("warp")) {
                if (args.length >= 1) {
                    String wpName = args[0];
                    warp warp = new warp(p, wpName);
                    if (Main.getData().warpExist(wpName)!=true) {
                        warp.warpYourself();
                        sender.sendMessage(pluginName + "§6 You warped yourself §6");
                    }else{
                        sender.sendMessage(pluginName + "§6 There is no warp with this name");
                    }
                }else {
                    sender.sendMessage(pluginName + "§6 The argument must be present");
                }
            }

            if (command.getName().equals("delwarp")) {
                if (args.length >= 1) {
                    String wpName = args[0];
                    delWarp delWarp = new delWarp(wpName);
                    delWarp.delwarp();
                    sender.sendMessage(pluginName + "§6 Succesfully deleted the warp §6");
                }else {
                    sender.sendMessage(pluginName + "§6 The argument must be present");
                }
            }

            if (command.getName().equals("warps")) {
                warplist warplist = new warplist();
                List<String> warps = warplist.returnWarpList();

                if (warplist.returnWarpList()!=null) {
                    for (String x : warps) {
                        sender.sendMessage(pluginName + " §6Name: " + "§b" + x + " §6Owner: §b" + Main.getData().returnOwner(x));
                    }
                }else {
                    sender.sendMessage(pluginName + "§6 There is no warp in the list§6");
                }
            }

        }else {
            System.out.println("Non puoi eseguire il comando da console");
        }

        return false;
    }
}
