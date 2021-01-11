package com.minecraftplugin.Executors;

import com.minecraftplugin.listener.commands.delWarp;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class delWarpExc implements CommandExecutor {

    private final String pluginName = "§5[EasyWarp]§5";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Command for delete a warp
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("warp.warp")) {
                if (args.length >= 1) {
                    String wpName = args[0];
                    delWarp delWarp = new delWarp(wpName);

                    if (Main.getData().warpExist(wpName) != true) {
                        //If in the config file the onlyOwner section is set to true, only the owner of the warp can delete it
                        //Only the owner of this warp can delete it
                        if (Boolean.parseBoolean(Main.getInstance().getConfig().getString("deletewarp.onlyOwner")) == true) {
                            if (p.getName().equals(Main.getData().returnOwner(wpName)) || p.isOp()) {
                                delWarp.delwarp();
                                sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("DeleteWarp.SuccesfulluDeleted"));
                            } else {
                                sender.sendMessage(pluginName + " §6 " + Main.getmessagies().getString("DeleteWarp.NotOwner"));
                            }
                            //Else everyone can delete it
                        } else {
                            delWarp.delwarp();
                            sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("DeleteWarp.SuccesfulluDeleted"));
                        }
                    } else {
                        sender.sendMessage(pluginName + " §6 " + Main.getmessagies().getString("DeleteWarp.NoWarp"));
                    }
                } else {
                    sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("DeleteWarp.Arg"));
                }
            }


            return true;
        }
        return false;
    }
}
