package com.minecraftplugin.listener;

import com.minecraftplugin.listener.commands.delWarp;
import com.minecraftplugin.listener.commands.setWarp;
import com.minecraftplugin.listener.commands.warp;
import com.minecraftplugin.listener.commands.warplist;
import com.minecraftplugin.minecraftplugin.Main;import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class commandListener implements CommandExecutor {

    private final String pluginName = "§5[EasyWarp]§5";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        Location lo = p.getLocation();

        if (sender instanceof Player) {

            //Command to set a warp
            if (command.getName().equals("setwarp")) {
                if (args.length >= 1) {
                    String wpName = args[0];
                    setWarp setWarp = new setWarp(p, lo, wpName);
                    if (Main.getData().warpExist(wpName)) {
                        setWarp.stWarp();
                        sender.sendMessage(pluginName + "§6 " +  Main.getmessagies().getString("SetWarpMessagies.SuccesfullySet"));
                    } else {
                        sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("SetWarpMessagies.AlredyExist"));
                    }
                }else {
                    sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("SetWarpMessagies.Arg"));
                }
            }

            //Command for warp
            if (command.getName().equals("warp")) {
                if (args.length >= 1) {
                    String wpName = args[0];
                    warp warp = new warp(p, wpName);
                    if (Main.getData().warpExist(wpName)!=true) {
                        warp.warpYourself();
                        sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("WarpMessagies.SyccesfullyWarped"));
                    }else{
                        sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("WarpMessagies.NoWarp"));
                    }
                }else {
                    sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("WarpMessagies.Arg"));
                }
            }

            //Command for delete a warp
            if (command.getName().equals("delwarp")) {
                if (args.length >= 1) {
                    String wpName = args[0];
                    delWarp delWarp = new delWarp(wpName);

                    if (Main.getData().warpExist(wpName)!=true) {
                        //If in the config file the onlyOwner section is set to true, only the owner of the warp can delete it
                        //Only the owner of this warp can delete it
                        if (Boolean.parseBoolean(Main.getInstance().getConfig().getString("deletewarp.onlyOwner")) == true) {
                            if (p.getName().equals(Main.getData().returnOwner(wpName)) || p.isOp()) {
                                delWarp.delwarp();
                                sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("DeleteWarp.SuccesfulluDeleted"));
                            }else {
                                sender.sendMessage(pluginName + " §6 " + Main.getmessagies().getString("DeleteWarp.NotOwner"));
                            }
                            //Else everyone can delete it
                        } else {
                            delWarp.delwarp();
                            sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("DeleteWarp.SuccesfulluDeleted"));
                        }
                    }else {
                        sender.sendMessage(pluginName + " §6 " + Main.getmessagies().getString("DeleteWarp.NoWarp"));
                    }
                }else {
                    sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("DeleteWarp.Arg"));
                }
            }

            //Command for warp list
            if (command.getName().equals("warps")) {
                warplist warplist = new warplist();
                List<String> warps = warplist.returnWarpList();

                if (warplist.returnWarpList()!=null) {
                    for (String x : warps) {
                        sender.sendMessage(pluginName + " §6Name: " + "§b" + x + " §6Owner: §b" + Main.getData().returnOwner(x));
                    }
                }else {
                    sender.sendMessage(pluginName + "§6 " + Main.getmessagies().getString("warps.NoWarpInList"));
                }
            }

            return true;

        }else {
            System.out.println("You cannot perform this command here");
        }

        return false;
    }
}
