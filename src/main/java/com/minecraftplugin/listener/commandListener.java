package com.minecraftplugin.listener;

import com.minecraftplugin.listener.commands.setWarp;
import com.minecraftplugin.listener.commands.warp;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandListener implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        Location lo = p.getLocation();
        String wpName = args[0];

        if (sender instanceof Player) {
            if (command.equals("setwarp")) {
                setWarp setWarp = new setWarp(p, lo, wpName);
                if (Main.getData().warpExist(wpName)) {
                    setWarp.stWarp();
                }else {
                    sender.sendMessage("§6Il warp esiste§6");
                }
            }

            if (command.equals("warp")) {
                warp warp = new warp(p, wpName);

                if (warp.buildlocation()!=null) {
                    warp.warpYourself();
                }
            }
        }else {
            System.out.println("§6Non puoi eseguire il comando da console§6");
        }

        return false;
    }
}
