package com.minecraftplugin.Executors;

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
            if (Boolean.parseBoolean(Main.getInstance().getConfig().getString("usePermission")) == true) {
                if (p.hasPermission("warp.warp")) {
                    warpList(p);
                }else {
                    p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warps.permission").replaceAll("&", "§"));
                }
            } else {
                if (command.getName().equals("warps")) {
                    warpList(p);
                }
            }
            return true;
        }
        return false;
    }

    public void warpList(Player p) {
        warplist warplist = new warplist();
        List<String> warps = warplist.returnWarpList();
        if (warplist.returnWarpList() != null) {
            for (String x : warps) {
                p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warps.warpList")
                        .replaceAll("\\{warps}", x).replaceAll("\\{OwnerName}", Main.getData().returnOwner(x)).replaceAll("&", "§"));
            }
        } else {
            p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warps.noWaroInList").replaceAll("&", "§"));
        }
    }


}

