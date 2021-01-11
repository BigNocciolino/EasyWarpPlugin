package com.minecraftplugin.Executors;

import com.minecraftplugin.listener.commands.warpReq;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class warprequestsList implements CommandExecutor {

    private final String pluginName = "§5[EasyWarp]§5";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("warp.request")) {
                warpReq warpReq = new warpReq();
                if (warpReq.pendingRequestst(p) != null) {
                    p.sendMessage( pluginName + ChatColor.GOLD + "Pending requests from: ");
                    for (String s : warpReq.pendingRequestst(p)) {
                        p.sendMessage(ChatColor.WHITE + "> " + s);
                    }
                }
            }
        }

        return false;
    }
}
