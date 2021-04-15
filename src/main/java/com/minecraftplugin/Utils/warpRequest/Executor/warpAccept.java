package com.minecraftplugin.Utils.warpRequest.Executor;

import com.minecraftplugin.Utils.warpRequest.Commands.acceptWarp;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class warpAccept implements CommandExecutor {

    /*
    * With this class you can handle the executor for accept a request pending to you
    *
    * (You accept to teleport the player tat sand to your location)
    */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //TODO messaggi custom yaml
        if (sender instanceof Player ) {
            //TODO switch to permission
            if (command.getName().equals("warpaccept")) {
                //Who accept
                Player p = (Player) sender;
                if (args.length > 0) {
                    //Who sended the request
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (target != p) {
                        acceptWarp accept = new acceptWarp(target, p);
                        acceptWarp.acceptRequest();
                        }else {
                            p.sendMessage("Non hai richieste da accettare");
                        }
                    }else {
                        p.sendMessage("Questo player non esiste");
                    }
                }else {
                    //TODO accetta tutte le richieste verso di te
                }
            }
            return true;
        }
        return false;
    }
}
