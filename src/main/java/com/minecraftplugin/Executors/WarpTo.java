package com.minecraftplugin.Executors;

import com.minecraftplugin.listener.commands.warpTo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpTo implements CommandExecutor {

    private final String pluginName = "§5[EasyWarp]§5";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("warp.warpto")) {
                if (args.length >= 1) {
                    Player recivier = Bukkit.getServer().getPlayer(args[0]);
                    if (recivier.getUniqueId().equals(p.getUniqueId())) {
                        sender.sendMessage(pluginName + ChatColor.RED + " You cant warp yourself yo you");
                    }else {
                        warpTo warpTo = new warpTo(p, recivier);
                        warpTo.warpTo();
                        sender.sendMessage(pluginName + ChatColor.GOLD + " You succesfully warped to " + recivier.getName());
                    }
                }else {
                    sender.sendMessage( pluginName + ChatColor.RED + " The argument musb be present");
                }
            }
        }


        return false;
    }
}
