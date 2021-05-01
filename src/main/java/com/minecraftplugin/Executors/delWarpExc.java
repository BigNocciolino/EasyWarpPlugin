package com.minecraftplugin.Executors;

import com.minecraftplugin.Utils.CustomMessagies;
import com.minecraftplugin.listener.commands.delWarp;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class delWarpExc implements CommandExecutor {

    private final String pluginName = Main.getInstance().getConfig().getString("messagiesPrefix").replaceAll("&", "§");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Command for delete a warp
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Boolean.parseBoolean(Main.getInstance().getConfig().getString("usePermission"))) {
                if (p.hasPermission("warp.warp")) {
                    deleteWarp(p, args[0]);
                }else {
                    CustomMessagies.sendMessage(p, "delwarp.permission");
                }
            } else {
                if (command.getName().equals("delwarp")) {
                    deleteWarp(p, args[0]);
                }
            }
            return true;
        }
        return false;
    }

    public void deleteWarp(Player p, String args) {
        if (args.length() >= 1) {
            String wpName = args;
            delWarp delWarp = new delWarp(wpName);
            if (Main.getData().warpExist(wpName) != true) {
                //If in the config file the onlyOwner section is set to true, only the owner of the warp can delete it
                //Only the owner of this warp can delete it
                if (Boolean.parseBoolean(Main.getInstance().getConfig().getString("deletewarp.onlyOwner")) == true) {
                    if (p.getName().equals(Main.getData().returnOwner(wpName)) || p.isOp()) {
                        delWarp.delwarp();
                        CustomMessagies.sendMessage(p,"delwarp.succesfullyDeleted");
                    } else {
                        CustomMessagies.sendMessage(p, "delwarp.onlyOwner");
                    }
                    //Else everyone can delete it
                } else {
                    delWarp.delwarp();
                    //succesullyDeleted
                    CustomMessagies.sendMessage(p,"delwarp.succesfullyDeleted");
                }
            } else {
                CustomMessagies.sendMessage(p, "delwarp.notExixst");
            }
        } else {
            CustomMessagies.sendMessage(p, "delwarp.arg");
        }
    }

}

