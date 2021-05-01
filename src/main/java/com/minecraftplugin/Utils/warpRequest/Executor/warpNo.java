package com.minecraftplugin.Utils.warpRequest.Executor;

import com.minecraftplugin.Utils.CustomMessagies;
import com.minecraftplugin.Utils.warpRequest.Commands.warpreq;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class warpNo implements CommandExecutor {

    //TODO remove all
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (command.getName().equals("warpno")){
                if (args.length > 0) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (target != p) {
                            warpreq req = warpreq.getRequestBySenderAndRecivier(p, target);
                            if (req != null) {
                                if (req.getResponder() == p) {
                                    req.destroy();
                                    CustomMessagies.sendMessage(p, "warpno.reject", "{player}", target.getName());
                                    CustomMessagies.sendMessage(target, "warpno.rejectRecivier", "{player}", target.getName());
                                } else {
                                    CustomMessagies.sendMessage(p, "warpno.notResponder", "{player}", target.getName());
                                }
                            }else {
                                CustomMessagies.sendMessage(p, "warpno.noRequest");
                            }
                        }else {
                            CustomMessagies.sendMessage(p, "warpno.toYou");
                        }
                    }else {
                        CustomMessagies.sendMessage(p, "warpno.offline");
                    }
                }else {
                    CustomMessagies.sendMessage(p, "warpno.arg");
                }
            }
            return true;
        }

        return false;
    }
}
