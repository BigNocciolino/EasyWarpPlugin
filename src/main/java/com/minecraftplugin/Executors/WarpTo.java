package com.minecraftplugin.Executors;

import com.minecraftplugin.listener.commands.warpTo;
import com.minecraftplugin.minecraftplugin.Main;
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
            //Ceck for the config
            if (Boolean.parseBoolean(Main.getInstance().getConfig().getString("usePermission")) == true) {
                if (p.hasPermission("warp.warpto")) {
                    //ags[0] stay for the name of the person, the only parameter of this command
                    goTo(p, args[0]);
                } else {
                    sender.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warpto.permission").replaceAll("&", "§"));
                }

            }else {
                if (command.getName().equals("warpto")) {
                    //Same method of up
                    goTo(p, args[0]);
                }
            }
        }
        return false;
    }

    //Method tho warp, I use this to clean the code
    public void goTo(Player p, String args) {
        if (args.length() >= 1) {
            Player recivier = Bukkit.getServer().getPlayer(args);
            if (recivier != null) {
                if (recivier.getUniqueId().equals(p.getUniqueId())) {
                    p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warpto.waroToYou").replaceAll("&", "§"));
                }else {
                    warpTo warpTo = new warpTo(p, recivier);
                    warpTo.warpTo();
                    p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warpto.succesfullyWarpedsender").replaceAll("\\{recivier}", recivier.getName()).replaceAll("&", "§"));
                    recivier.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warpto.succesfullyWarpedrecivier").replaceAll("\\{player}", p.getName()).replaceAll("&", "§"));
                }
            }else {
                p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warpto.offline").replaceAll("&","§"));
            }
        }else {
            p.sendMessage(pluginName + Main.getInstance().getConfig().getString("messagies.warpto.arg").replaceAll("&","§"));
        }
    }
}
