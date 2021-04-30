package com.minecraftplugin.Utils.warpRequest.Executor;

import com.minecraftplugin.Utils.CustomMessagies;
import com.minecraftplugin.Utils.warpRequest.Commands.acceptWarp;
import com.minecraftplugin.Utils.warpRequest.Commands.warpreq;
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
                if (warpreq.getRequestsByRevier(p).size() > 1) {
                    if (args.length > 0) {
                        //Who sended the request
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            if (target != p) {
                                warpreq req = warpreq.getRequestBySenderAndRecivier(p, target);
                                if (req != null) {
                                    if (p == req.getResponder()) {
                                        acceptWarp accept = new acceptWarp(req);
                                        accept.acceptRequest();
                                    } else {
                                        CustomMessagies.sendMessage(p, "acceptWarp.toYou");
                                    }
                                } else {
                                    CustomMessagies.sendMessage(p, "acceptWarp.noRequest");
                                }
                            } else {
                                CustomMessagies.sendMessage(p, "acceptWarp.toYou");
                            }
                        } else {
                            CustomMessagies.sendMessage(p, "acceptWarp.offline");
                        }
                    }else {
                        CustomMessagies.sendMessage(p, "acceptWarp.arg");
                    }
                }else {
                    warpreq request = warpreq.getRequestsByRevier(p).get(0);
                    acceptWarp accept = new acceptWarp(request);
                    accept.acceptRequest();
                }
            }
            return true;
        }
        return false;
    }
}
