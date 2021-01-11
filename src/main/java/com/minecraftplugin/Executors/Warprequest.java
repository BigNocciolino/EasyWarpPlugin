package com.minecraftplugin.Executors;

import com.minecraftplugin.listener.commands.warpReq;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Warprequest implements CommandExecutor {

    private final String pluginName = "§5[EasyWarp]§5";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("warp.request")) {
                if (args.length == 1) {
                    Player recivier = Bukkit.getServer().getPlayerExact(args[0]);
                    if (recivier == null)  {
                        sender.sendMessage(pluginName + ChatColor.GOLD + " this player is offline");
                    }else {
                        warpReq warpReq = new warpReq();
                        warpReq.sendRequest(p, recivier);
                    }
                }else {
                    sender.sendMessage( pluginName + " Insert the player name");
                }
            }

            if (p.hasPermission("warp.request")) {
                warpReq warpReq = new warpReq();
                if (args.length == 0) {
                    warpReq.acceptRequest(p, "all");
                }else if (args.length == 1) {
                    Player recivier = Bukkit.getServer().getPlayerExact(args[0]);
                    if (recivier != null) {
                        warpReq.acceptRequest(p, recivier.getName());
                    }
                }
            }

            if (p.hasPermission("warp.request")) {
                if (args.length == 0) {
                    warpReq warpReq = new warpReq();
                    warpReq.denyRequest(p, "all");
                    if (args.length == 1) {
                        Player recivier = Bukkit.getServer().getPlayerExact(args[0]);
                        warpReq.denyRequest(p, recivier.getName());
                    }
                }
            }
            return true;
        }
        return false;
    }
}
