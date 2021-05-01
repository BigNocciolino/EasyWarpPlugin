package com.minecraftplugin.Utils.warpRequest.Executor;

import com.minecraftplugin.Utils.CustomMessagies;
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
                                    CustomMessagies.sendMessage(p, "warpRemove.removed");
                                    CustomMessagies.sendMessage(target, "warpRemove.removedRecivier", "{player}", p.getName());
                                }else {
                                    CustomMessagies.sendMessage(p, "warpRemove.noRecivier", "{player}", target.getName());
                                }
                            }else {
                                CustomMessagies.sendMessage(p, "warpRemove.noRequest");
                            }
                        }else {
                            CustomMessagies.sendMessage(p, "warpRemove.toYou");
                        }
                    }else {
                        CustomMessagies.sendMessage(p, "warpRemove.offline");
                    }
                }else {
                    CustomMessagies.sendMessage(p, "warpRemove.offline");
                }
            }else {
                warpreq request = warpreq.getRequestBySender(p).get(0);
                Player target = request.getResponder();
                request.destroy();
                CustomMessagies.sendMessage(p, "warpRemove.removed");
                CustomMessagies.sendMessage(target, "warpRemove.removedRecivier", "{player}", p.getName());
            }

            return true;
        }

        return false;
    }
}
