package com.minecraftplugin.Executors;

import com.minecraftplugin.listener.commands.setWarp;
import com.minecraftplugin.listener.commands.warp;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpExc implements CommandExecutor {

    private final String pluginName = "§5[EasyWarp]§5";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Command for warp
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("warp.warp")) {
                if (args.length >= 1) {
                    String wpName = args[0];
                    warp warp = new warp(p, wpName);
                    if (Main.getData().warpExist(wpName) != true) {
                        warp.warpYourself();
                        sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("WarpMessagies.SyccesfullyWarped"));
                    } else {
                        sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("WarpMessagies.NoWarp"));
                    }
                } else {
                    sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("WarpMessagies.Arg"));
                }
            }
            return true;
        }
        return false;
    }
}
