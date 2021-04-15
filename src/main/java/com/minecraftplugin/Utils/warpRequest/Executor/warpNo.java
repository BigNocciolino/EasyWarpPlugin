package com.minecraftplugin.Utils.warpRequest.Executor;

import com.minecraftplugin.Utils.warpRequest.Commands.warpreq;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class warpNo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (command.getName().equals("warpno")){
                if (args.length > 0) {
                    Player target = Bukkit.getPlayer(args[0]);
                    warpreq req = warpreq.getRequestBySenderAndRecivier(p, target);
                    if (req.getRequester() == p) {
                        req.removeRequest(req);
                    }else {
                        p.sendMessage("You have no request from this player");
                    }
                }
            }
            return true;
        }

        return false;
    }
}
