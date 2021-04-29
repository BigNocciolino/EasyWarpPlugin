package com.minecraftplugin.Utils.warpRequest.Executor;

import com.minecraftplugin.Utils.warpRequest.Commands.warpreq;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class warpRemove implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (warpreq.getRequestBySender(p).size() > 1) {
                if (args.length > 0) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null){
                        if (target != p){
                            warpreq req = warpreq.getRequestBySenderAndRecivier(p, target);
                            if (req != null){
                                if (p == req.getRequester()) {
                                    req.destroy();
                                    p.sendMessage("Request destroyed");
                                    target.sendMessage(p.getName() + " removed the request");
                                }
                            }else {
                                p.sendMessage("You have no request");
                            }
                        }else {
                            p.sendMessage("Why");
                        }
                    }else {
                        p.sendMessage("This player doesent exist");
                    }
                }else {
                    p.sendMessage("Inserisci in player");
                }
            }else {
                warpreq request = warpreq.getRequestBySender(p).get(0);
                Player target = request.getResponder();
                request.destroy();
                p.sendMessage("Removed the request");
                target.sendMessage(p.getName() + " removed the request");
            }

            return true;
        }

        return false;
    }
}
