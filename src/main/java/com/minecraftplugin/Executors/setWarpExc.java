package com.minecraftplugin.Executors;

import com.minecraftplugin.listener.commands.setWarp;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setWarpExc implements CommandExecutor {

    private final String pluginName = "§5[EasyWarp]§5";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            Location lo = p.getLocation();
            //Command to set a warp
            if (p.hasPermission("warp.warp")) {
                if (args.length >= 1) {
                    String wpName = args[0];
                    setWarp setWarp = new setWarp(p, lo, wpName);
                    if (Main.getData().warpExist(wpName)) {
                        setWarp.stWarp();
                        sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("SetWarpMessagies.SuccesfullySet"));
                    } else {
                        sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("SetWarpMessagies.AlredyExist"));
                    }
                } else {
                    sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("SetWarpMessagies.Arg"));
                }
            }
            return true;
        }

        return false;
    }
}
