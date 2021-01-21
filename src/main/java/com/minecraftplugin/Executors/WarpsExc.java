package com.minecraftplugin.Executors;

import com.minecraftplugin.GUI.warpGui;
import com.minecraftplugin.listener.commands.warplist;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WarpsExc implements CommandExecutor {

    private final String pluginName = "§5[EasyWarp]§5";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            //Command for warp list
            if (p.hasPermission("warp.warp")) {
                warplist warplist = new warplist();
                List<String> warps = warplist.returnWarpList();
                warpGui wp = new warpGui();
                if (warplist.returnWarpList() != null) {
                    for (String x : warps) {
                        sender.sendMessage(pluginName + " §6Name: " + "§b" + x + " §6Owner: §b" + Main.getData().returnOwner(x));
                    }
                } else {
                    sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("warps.NoWarpInList"));
                }
            }

            if (sender.hasPermission("warp.warp")) {
                if (args[0].equals("gui")) {
                    warpGui warpGui = new warpGui();
                }
            }

            return true;
        }

        return false;
    }
}
