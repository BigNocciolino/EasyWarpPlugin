package com.minecraftplugin.Utils.warpRequest.Executor;

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
                                }else {
                                    p.sendMessage("Tou cannot accept your request");
                                }
                            }else{
                                p.sendMessage("There is no request");
                            }
                        }else {
                            p.sendMessage("Why are you doing this");
                        }
                    }else {
                        p.sendMessage("Questo player non esiste");
                    }
                }else {
                    //Accept the last request
                    if (warpreq.getRequestBySender(p).size() == 1){
                        warpreq request = warpreq.getRequestBySender(p).get(0);
                        acceptWarp accept_all = new acceptWarp(request);
                        accept_all.acceptRequest();
                    }
                }
            }
            return true;
        }
        return false;
    }
}
